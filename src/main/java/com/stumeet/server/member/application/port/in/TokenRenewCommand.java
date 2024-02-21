package com.stumeet.server.member.application.port.in;

import jakarta.validation.constraints.NotBlank;

public record TokenRenewCommand(
        @NotBlank(message = "액세스 토큰을 입력해주세요")
        String accessToken,

        @NotBlank(message = "리프레시 토큰을 입력해주세요")
        String refreshToken
) {
}
