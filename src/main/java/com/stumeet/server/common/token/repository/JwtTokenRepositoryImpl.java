package com.stumeet.server.common.token.repository;

import com.stumeet.server.common.exception.model.BusinessException;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import static com.stumeet.server.common.response.ErrorCode.NOT_MATCHED_TOKEN_EXCEPTION;

@Repository
@RequiredArgsConstructor
public class JwtTokenRepositoryImpl implements JwtTokenRepository {

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
            throw new BusinessException(NOT_MATCHED_TOKEN_EXCEPTION);
        }

        return refreshToken;
    }

    @Override
    public String getByRefreshToken(String refreshToken) {
        return redisTemplate.opsForValue().get(refreshToken);
    }

    @Override
    public void deleteByToken(String token) {
        boolean isDeleted = Boolean.TRUE.equals(redisTemplate.delete(token));
        if (!isDeleted) {
            throw new BusinessException(NOT_MATCHED_TOKEN_EXCEPTION);
        }
    }
}
