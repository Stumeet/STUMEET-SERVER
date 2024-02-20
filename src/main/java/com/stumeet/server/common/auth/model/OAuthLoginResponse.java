package com.stumeet.server.common.auth.model;

public record OAuthLoginResponse(
        String accessToken,
        String refreshToken,
        boolean isFirstLogin
) {
}
