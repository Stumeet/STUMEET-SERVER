package com.stumeet.server.common.token.service;

import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.common.token.exception.TokenReuseDetectedException;
import com.stumeet.server.common.token.repository.JwtTokenRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final JwtTokenRepository jwtTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void saveNewTokens(String accessToken, String refreshToken) {
        jwtTokenRepository.save(accessToken, refreshToken);
    }
    public void addToBlackList(String refreshToken, String renewAccessToken) {
        jwtTokenRepository.save(refreshToken, renewAccessToken);
    }

    public void validateRefreshToken(String accessToken, String refreshToken) {
        validateTokenExpiration(refreshToken);
        validateTokenNotInBlackList(refreshToken);
        validateTokenMatched(accessToken, refreshToken);
    }

    private void validateTokenExpiration(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new BusinessException(ErrorCode.EXPIRED_REFRESH_TOKEN_EXCEPTION);
        }
    }

    private void validateTokenMatched(String accessToken, String refreshToken) {
        String savedRefreshToken = jwtTokenRepository.getByAccessToken(accessToken);

        if (!refreshToken.equals(savedRefreshToken)) {
            throw new BusinessException(ErrorCode.NOT_MATCHED_REFRESH_TOKEN_EXCEPTION);
        }
    }

    private void validateTokenNotInBlackList(String refreshToken) {
        String savedAccessToken = jwtTokenRepository.getByRefreshToken(refreshToken);

        if (savedAccessToken != null) {
            deleteUsedTokenByAccessToken(savedAccessToken);
            deleteFromBlackList(refreshToken);

            Long memberId = Long.parseLong(jwtTokenProvider.getSubject(refreshToken));
            throw new TokenReuseDetectedException(memberId);
        }
    }

    public void deleteUsedTokenByAccessToken(String accessToken) {
        jwtTokenRepository.deleteByToken(accessToken);
    }

    public void deleteFromBlackList(String refreshToken) {
        jwtTokenRepository.deleteByToken(refreshToken);
    }
}
