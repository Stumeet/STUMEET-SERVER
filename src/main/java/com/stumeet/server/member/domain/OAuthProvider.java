package com.stumeet.server.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum OAuthProvider {
    KAKAO("kakao"),
    APPLE("apple");

    private final String provider;

    public static OAuthProvider findByProvider(String provider) {
        return Arrays.stream(values())
                .filter(p -> p.getProvider().equals(provider))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 OAuth provider 입니다."));
    }
}
