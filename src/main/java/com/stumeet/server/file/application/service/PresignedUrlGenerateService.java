package com.stumeet.server.file.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.adapter.in.response.PresignedUrlResponses;
import com.stumeet.server.file.application.port.in.PresignedUrlGenerateUseCase;
import com.stumeet.server.file.adapter.in.response.PresignedUrlResponse;
import com.stumeet.server.file.application.port.in.command.PresignedUrlCommand;
import com.stumeet.server.file.application.port.in.command.PresignedUrlCommands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class PresignedUrlGenerateService implements PresignedUrlGenerateUseCase {

    private final PresignedUrlGenerateAsyncService presignedUrlGenerateAsyncService;

    @Override
    public PresignedUrlResponses generatePresignedUrls(PresignedUrlCommands commands) {
        List<CompletableFuture<PresignedUrlResponse>> futures = new ArrayList<>();

        for (PresignedUrlCommand command : commands.requests()) {
            CompletableFuture<PresignedUrlResponse> future =
                    presignedUrlGenerateAsyncService.generatePresignedUrl(command);
            futures.add(future);
        }

        List<PresignedUrlResponse> responses = futures.stream()
                .map(CompletableFuture::join)
                .toList();

        return new PresignedUrlResponses(responses);
    }
}