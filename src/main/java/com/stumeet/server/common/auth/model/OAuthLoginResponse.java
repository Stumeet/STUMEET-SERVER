package com.stumeet.server.common.auth.model;

public record OAuthLoginResponse(
        String accessToken,
        boolean isFirstLogin
) {
}
