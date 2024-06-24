package com.stumeet.server.common.token.service;

import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.common.token.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void save(String accessToken, String refreshToken) {
        refreshTokenRepository.save(accessToken, refreshToken);
    }

    public void validateRefreshToken(String accessToken, String refreshToken) {
        String savedRefreshToken = refreshTokenRepository.getByAccessToken(accessToken);

        if (!refreshToken.equals(savedRefreshToken)) {
            throw new BusinessException(ErrorCode.NOT_MATCHED_REFRESH_TOKEN_EXCEPTION);
        }
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new BusinessException(ErrorCode.EXPIRED_REFRESH_TOKEN_EXCEPTION);
        }
    }

    public void deleteByAccessToken(String accessToken) {
        refreshTokenRepository.deleteByAccessToken(accessToken);
    }
}
