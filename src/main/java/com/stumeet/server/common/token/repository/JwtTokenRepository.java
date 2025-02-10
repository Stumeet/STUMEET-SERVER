package com.stumeet.server.common.token.repository;

public interface JwtTokenRepository {

    void save(String accessToken, String refreshToken);

    String getByAccessToken(String accessToken);

    String getByRefreshToken(String refreshToken);

    boolean isExist(String token);

    void deleteByToken(String accessToken);
}
