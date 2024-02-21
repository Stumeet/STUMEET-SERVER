package com.stumeet.server.common.token.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;


    @Override
    public void save(String accessToken, String refreshToken) {
        redisTemplate.opsForValue()
                .set(accessToken, refreshToken);
    }

    @Override
    public String getByAccessToken(String accessToken) {
        String refreshToken = redisTemplate.opsForValue().get(accessToken);
        if (refreshToken == null) {
            throw new IllegalArgumentException("전달받은 Access Token과 매칭되는 Refresh Token이 존재하지 않습니다.");
        }

        return refreshToken;
    }

    @Override
    public void deleteByAccessToken(String accessToken) {
        boolean isDeleted = Boolean.TRUE.equals(redisTemplate.delete(accessToken));
        if (!isDeleted) {
            throw new IllegalArgumentException("전달받은 Access Token과 매칭되는 Refresh Token이 존재하지 않습니다.");
        }
    }
}
