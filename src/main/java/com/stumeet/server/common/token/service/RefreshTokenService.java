package com.stumeet.server.common.token.service;

import com.stumeet.server.common.token.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void save(String accessToken, String refreshToken) {
        refreshTokenRepository.save(accessToken, refreshToken);
    }
}
