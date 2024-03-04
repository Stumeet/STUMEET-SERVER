package com.stumeet.server.file.adapter.out;

import static com.stumeet.server.common.exception.handler.GlobalExceptionHandler.*;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.annotation.StorageAdapter;
import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.exception.model.NotImplementedException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.common.util.FileUtil;
import com.stumeet.server.file.application.port.out.FileCommandPort;
import com.stumeet.server.file.application.port.out.FileUrl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@StorageAdapter
@RequiredArgsConstructor
@Slf4j
public class S3ImageStorageAdapter implements FileCommandPort {

	@Value("${spring.cloud.config.server.awss3.bucket}")
	private String bucket;

	@Value("${spring.cloud.config.server.awss3.endpoint}")
	private String endpoint;

	private final S3Client s3Client;

	@Override
	public FileUrl uploadImageFile(MultipartFile multipartFile, String directoryPath) {
		String originalFileName = multipartFile.getOriginalFilename();
		String key = FileUtil.createFileName(directoryPath, originalFileName);

		PutObjectRequest objectRequest = PutObjectRequest.builder()
			.contentType(FileUtil.getValidImageContentType(multipartFile.getContentType()))
			.bucket(bucket)
			.key(key)
			.build();

		try {
			s3Client.putObject(
				objectRequest,
				RequestBody
					.fromInputStream(
						multipartFile.getInputStream(),
						multipartFile.getSize()));
		} catch (IOException e) {
			log.warn(WARN_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());
			log.debug(e.getMessage(), e);

			throw new BusinessException(ErrorCode.UPLOAD_FILE_FAIL_ERROR);
		}

		return getS3FileUrl(key);
	}

	@Override
	public FileUrl uploadImageFiles(List<MultipartFile> multipartFileList, String directoryPath) {
		throw new NotImplementedException();
	}

	private FileUrl getS3FileUrl(String key) {
		return new FileUrl(String.format("%s/%s", endpoint, key));
	}
}
