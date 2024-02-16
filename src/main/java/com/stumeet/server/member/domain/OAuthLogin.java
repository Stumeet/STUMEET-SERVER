package com.stumeet.server.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class OAuthLogin {
    private Long id;

    private Member member;

    private OAuthProvider providerName;

    private String providerId;
}
