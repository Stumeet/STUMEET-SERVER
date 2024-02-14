package com.stumeet.server.member.domain;

import java.util.Arrays;

public enum OAuthProvider {
    KAKAO,
    APPLE;

    public static OAuthProvider findByProvider(String provider) {
        return Arrays.stream(values())
                .filter(p -> p.toString().toLowerCase().equals(provider))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 OAuth provider 입니다."));
    }
}
