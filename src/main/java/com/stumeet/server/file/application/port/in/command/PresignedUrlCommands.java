package com.stumeet.server.file.application.port.in.command;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PresignedUrlCommands(
        @NotNull(message = "Presigned URL을 요청할 이미지 정보를 입력해주세요.")
        @Size(min = 1, max = 5, message = "요청 가능한 Presigned URL 개수는 최소 1개, 최대 5개 입니다.")
        List<PresignedUrlCommand> commands
) {
}