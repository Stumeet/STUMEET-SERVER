package com.stumeet.server.file.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.util.FileValidator;
import com.stumeet.server.file.application.port.dto.FileUrl;
import com.stumeet.server.file.application.port.in.PresignedUrlGenerateUseCase;
import com.stumeet.server.file.application.port.in.command.PresignedUrlCommand;
import com.stumeet.server.file.application.port.in.response.PresignedUrlResponse;
import com.stumeet.server.file.application.port.out.PresignedUrlGeneratePort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class PresignedUrlGenerateService implements PresignedUrlGenerateUseCase {

    private final PresignedUrlGeneratePort presignedUrlGeneratePort;


    @Override
    public PresignedUrlResponse generatePresignedUrl(PresignedUrlCommand command) {
        FileValidator.isValidImageFile(command.fileName());

        FileUrl fileUrl = presignedUrlGeneratePort.generatePresignedUrl(command.path(), command.fileName());

        return PresignedUrlResponse.builder()
                .url(fileUrl.url())
                .build();
    }
}
