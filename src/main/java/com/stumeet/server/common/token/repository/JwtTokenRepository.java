package com.stumeet.server.common.token.repository;

public interface JwtTokenRepository {

    void save(String accessToken, String refreshToken);

    String getByAccessToken(String accessToken);

    String getByRefreshToken(String refreshToken);

    void deleteByToken(String accessToken);
}
