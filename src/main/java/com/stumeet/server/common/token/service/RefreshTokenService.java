package com.stumeet.server.common.token.service;

import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.common.token.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void save(String accessToken, String refreshToken) {
        refreshTokenRepository.save(accessToken, refreshToken);
    }

    public String getRefreshToken(String accessToken, String refreshToken) {
        String savedRefreshToken = refreshTokenRepository.getByAccessToken(accessToken);

        if (!refreshToken.equals(savedRefreshToken)) {
            throw new IllegalArgumentException(
                    MessageFormat.format("리프레시 토큰이 일치하지 않습니다.\n전달받은 토큰 : {} 저장된 토큰{}", refreshToken, savedRefreshToken)
            );
        }
        jwtTokenProvider.validateToken(refreshToken);

        return savedRefreshToken;
    }

    public void deleteByAccessToken(String accessToken) {
        refreshTokenRepository.deleteByAccessToken(accessToken);
    }
}
