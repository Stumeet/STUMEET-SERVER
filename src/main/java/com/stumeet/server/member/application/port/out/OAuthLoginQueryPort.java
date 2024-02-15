package com.stumeet.server.member.application.port.out;

public interface OAuthLoginQueryPort {
    boolean existsByProviderId(String providerId);
}
