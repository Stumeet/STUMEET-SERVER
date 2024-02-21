package com.stumeet.server.common.token.repository;

public interface RefreshTokenRepository {

    void save(String accessToken, String refreshToken);

    String getByAccessToken(String accessToken);

    void deleteByAccessToken(String accessToken);
}
