package com.stumeet.server.file.application.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.stumeet.server.common.util.FileValidator;
import com.stumeet.server.file.adapter.in.response.PresignedUrlResponse;
import com.stumeet.server.file.application.port.dto.FileUrl;
import com.stumeet.server.file.application.port.in.command.PresignedUrlCommand;
import com.stumeet.server.file.application.port.out.PresignedUrlGeneratePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PresignedUrlGenerateAsyncService {

    private final PresignedUrlGeneratePort presignedUrlGeneratePort;

    @Async
    public CompletableFuture<PresignedUrlResponse> generatePresignedUrl(PresignedUrlCommand command) {
        FileValidator.validateImageFile(command.fileName());

        FileUrl fileUrl = presignedUrlGeneratePort.generatePresignedUrl(command.path(), command.fileName());

        return CompletableFuture.completedFuture(
                PresignedUrlResponse.builder()
                        .url(fileUrl.url())
                        .build());
    }
}
