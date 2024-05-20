package com.stumeet.server.file.application.port.in.command;

import com.stumeet.server.file.domain.FileManagementPath;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PresignedUrlCommand(
        @NotBlank(message = "파일 관리 경로를 입력해주세요")
        FileManagementPath path,
        @NotBlank(message = "파일 이름을 입력해주세요")
        String fileName
) {
}
