package com.stumeet.server.file.application.service;

import com.stumeet.server.file.application.port.dto.FileUrl;
import com.stumeet.server.file.application.port.in.command.PresignedUrlCommand;
import com.stumeet.server.file.adapter.in.response.PresignedUrlResponse;
import com.stumeet.server.file.application.port.out.PresignedUrlGeneratePort;
import com.stumeet.server.file.domain.exception.InvalidFileException;
import com.stumeet.server.stub.FileStub;
import com.stumeet.server.template.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;

class PresignedUrlGenerateServiceTest extends UnitTest {

    @InjectMocks
    private PresignedUrlGenerateService presignedUrlGenerateService;

    @Mock
    private PresignedUrlGeneratePort presignedUrlGeneratePort;

    @Nested
    @DisplayName("generatePresignedUrl 메소드는")
    class GeneratePresignedUrl {

        @Test
        @DisplayName("[성공] PresignedUrl을 생성한다.")
        void successTest() {
            PresignedUrlCommand request = FileStub.getPresignedUrlCommand();
            FileUrl want = FileStub.getPresignedUrl();

            given(presignedUrlGeneratePort.generatePresignedUrl(request.path(), request.fileName()))
                    .willReturn(want);

            PresignedUrlResponse got = presignedUrlGenerateService.generatePresignedUrl(request);

            assertThat(got.url()).isEqualTo(want.url());
        }

        @ParameterizedTest
        @MethodSource("com.stumeet.server.stub.FileStub#getInvalidFileTestArguments")
        @DisplayName("[실패] 파일 이름이 유효하지 않은 경우 예외가 발생합니다.")
        void invalidFileTest(String documentPath, PresignedUrlCommand invalidFileRequest, InvalidFileException e) {
            assertThatCode(() -> presignedUrlGenerateService.generatePresignedUrl(invalidFileRequest))
                    .isInstanceOf(e.getClass())
                    .hasMessage(e.getMessage());
        }
    }
}