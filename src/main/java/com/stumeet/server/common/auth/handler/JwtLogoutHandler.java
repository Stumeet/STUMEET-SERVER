package com.stumeet.server.common.auth.handler;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.common.token.service.RefreshTokenService;
import com.stumeet.server.common.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtLogoutHandler implements LogoutHandler {

    private final RefreshTokenService refreshTokenService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader(AuthenticationHeader.ACCESS_TOKEN.getName());

        String accessToken = JwtUtil.resolveToken(token);
        if (accessToken == null) {
            throw new BadCredentialsException("유효하지 않은 토큰입니다. token : " + token);
        }

        refreshTokenService.deleteByAccessToken(accessToken);
    }
}
