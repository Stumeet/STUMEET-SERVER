package com.stumeet.server.member.domain;

import com.stumeet.server.common.auth.exception.NotExistsOAuthProviderException;
import com.stumeet.server.common.response.ErrorCode;
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
                .orElseThrow(() -> new NotExistsOAuthProviderException(ErrorCode.NOT_EXIST_OAUTH_PROVIDER));
    }
}
