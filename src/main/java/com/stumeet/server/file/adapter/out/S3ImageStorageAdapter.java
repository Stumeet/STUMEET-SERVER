package com.stumeet.server.file.adapter.out;

import com.stumeet.server.common.annotation.StorageAdapter;
import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.exception.model.NotImplementedException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.common.util.FileUtil;
import com.stumeet.server.common.util.FileValidator;
import com.stumeet.server.file.application.port.dto.FileUrl;
import com.stumeet.server.file.application.port.out.FileCommandPort;
import com.stumeet.server.file.application.port.out.PresignedUrlGeneratePort;
import com.stumeet.server.file.domain.FileManagementPath;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@StorageAdapter
@RequiredArgsConstructor
@Slf4j
public class S3ImageStorageAdapter implements FileCommandPort, PresignedUrlGeneratePort {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    @Value("${spring.cloud.config.server.aws.s3.bucket}")
    private String bucket;
    @Value("${spring.cloud.config.server.aws.s3.endpoint}")
    private String endpoint;
    @Value("${presigned.url.expired-time}")
    private int expiredTime;

    @Override
    public FileUrl uploadImageFile(MultipartFile file, String directoryPath) {
        FileValidator.validateImageFile(file);
        String key = FileUtil.generateKey(directoryPath, file.getOriginalFilename());

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .contentType(file.getContentType())
                .bucket(bucket)
                .key(key)
                .build();

        try {
            s3Client.putObject(
                    objectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            return getS3FileUrl(key);
        } catch (IOException e) {
            throw new BusinessException(e, ErrorCode.FILE_IO_ERROR);
        } catch (Exception e) {
            throw new BusinessException(e, ErrorCode.UPLOAD_FILE_FAIL_ERROR);
        }
    }

    private FileUrl getS3FileUrl(String key) {
        return new FileUrl(String.format("%s/%s", endpoint, key));
    }

    @Override
    public List<FileUrl> uploadImageFiles(List<MultipartFile> multipartFileList, String directoryPath) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteFolder(String folder) {
        List<ObjectIdentifier> objectIdentifiers = getObjectIdentifiers(folder);

        if (objectIdentifiers.isEmpty()) {
            return;
        }

        DeleteObjectsRequest deleteObjectsRequest = DeleteObjectsRequest.builder()
                .bucket(bucket)
                .delete(builder -> builder.objects(objectIdentifiers))
                .build();

        s3Client.deleteObjects(deleteObjectsRequest);
    }

    private List<ObjectIdentifier> getObjectIdentifiers(String prefix) {
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(bucket)
                .prefix(prefix)
                .build();

        return s3Client.listObjectsV2(listObjectsRequest).contents().stream()
                .map(object -> ObjectIdentifier.builder()
                        .key(object.key())
                        .build())
                .toList();
    }

    @Override
    public FileUrl generatePresignedUrl(FileManagementPath path, String fileName) {
        String key = FileUtil.generateKey(path.getPath(), fileName);

        PresignedPutObjectRequest request = s3Presigner.presignPutObject(
                p -> p.signatureDuration(Duration.ofSeconds(expiredTime))
                        .putObjectRequest(pr -> pr.bucket(bucket).key(key))
        );

        return new FileUrl(request.url().toString());
    }
}
