package com.stumeet.server.file.application.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.stumeet.server.file.adapter.in.response.PresignedUrlResponse;
import com.stumeet.server.file.adapter.in.response.PresignedUrlResponses;
import com.stumeet.server.file.application.port.in.command.PresignedUrlCommands;
import com.stumeet.server.stub.FileStub;
import com.stumeet.server.template.UnitTest;

class PresignedUrlGenerateServiceTest extends UnitTest {

    @InjectMocks
    private PresignedUrlGenerateService presignedUrlGenerateService;

    @Mock
    private PresignedUrlGenerateAsyncService presignedUrlGenerateAsyncService;

    @Nested
    @DisplayName("generatePresignedUrls 메소드는")
    class GeneratePresignedUrls {

        @Test
        @DisplayName("[성공] 비동기로 Presigned URL 목록을 생성한다.")
        void success() {
            PresignedUrlCommands commands = FileStub.getPresignedUrlCommands();
            PresignedUrlResponses want = FileStub.getPresignedUrlResponses();

            given(presignedUrlGenerateAsyncService.generatePresignedUrl(any()))
                    .willReturn(
                            CompletableFuture.completedFuture(
                                    new PresignedUrlResponse(FileStub.getPresignedUrl().url())));

            PresignedUrlResponses got = presignedUrlGenerateService.generatePresignedUrls(commands);

            assertThat(got).isEqualTo(want);
        }
    }
}