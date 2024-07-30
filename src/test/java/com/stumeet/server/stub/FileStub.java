package com.stumeet.server.stub;

import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.file.adapter.in.response.PresignedUrlResponse;
import com.stumeet.server.file.adapter.in.response.PresignedUrlResponses;
import com.stumeet.server.file.application.port.dto.FileUrl;
import com.stumeet.server.file.application.port.in.command.PresignedUrlCommand;
import com.stumeet.server.file.application.port.in.command.PresignedUrlCommands;
import com.stumeet.server.file.domain.FileManagementPath;
import com.stumeet.server.file.domain.exception.InvalidFileException;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class FileStub {
    private FileStub() {

    }


    public static FileUrl getFileUrl() {
        return new FileUrl("http://localhost:4572/user/1/profile/2024030416531039839905-b7e8-4ad3-9552-7d9cbc01cb14-test.jpg");
    }

    public static FileUrl getPresignedUrl() {
        return new FileUrl("http://127.0.0.1:52741/stumeet/activity/20240428213207105c0583-502b-4b59-8cc3-68a31eb9910f-test.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240428T123207Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=test%2F20240428%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=987d171e4e2bca89e7310f5d3e20dc9bf5e78b50a2510d0c0e7f2c05e0b8cc92");

    }

    public static PresignedUrlCommand getPresignedUrlCommand() {
        return PresignedUrlCommand.builder()
                .path(FileManagementPath.STUDY_ACTIVITY)
                .fileName("test.jpg")
                .build();
    }

    public static PresignedUrlCommands getPresignedUrlCommands() {
        return new PresignedUrlCommands(
                List.of(
                        getPresignedUrlCommand(),
                        getPresignedUrlCommand(),
                        getPresignedUrlCommand()
                )
        );
    }

    public static PresignedUrlResponse getPresignedUrlResponse() {
        return PresignedUrlResponse.builder()
                .url(getPresignedUrl().url())
                .build();
    }

    public static PresignedUrlResponses getPresignedUrlResponses() {
        return new PresignedUrlResponses(
                List.of(
                        getPresignedUrlResponse(),
                        getPresignedUrlResponse(),
                        getPresignedUrlResponse()
                )
        );
    }

    public static Stream<Arguments> getInvalidFileTestArguments() {
        return Stream.of(
                Arguments.of(
                        "presigned-url-generate/fail/invalid-file-name",
                        PresignedUrlCommand.builder()
                                .path(FileManagementPath.STUDY_ACTIVITY)
                                .fileName("test")
                                .build(),
                        new InvalidFileException(ErrorCode.INVALID_FILE_NAME_EXCEPTION)
                ),
                Arguments.of(
                        "presigned-url-generate/fail/invalid-file-extension",
                        PresignedUrlCommand.builder()
                                .path(FileManagementPath.STUDY_ACTIVITY)
                                .fileName("test.pxp")
                                .build(),
                        new InvalidFileException(ErrorCode.INVALID_FILE_EXTENSION_EXCEPTION)
                )
        );
    }
}
