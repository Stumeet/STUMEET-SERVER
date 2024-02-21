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
}
