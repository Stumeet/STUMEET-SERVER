package com.stumeet.server.common.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stumeet.server.common.auth.model.OAuthLoginResponse;
import com.stumeet.server.common.auth.token.StumeetAuthenticationToken;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.member.domain.UserRole;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        StumeetAuthenticationToken token = (StumeetAuthenticationToken) authentication;
        boolean isFirstLogin = token.getPrincipal()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(UserRole.FIRST_LOGIN.toString()));

        ApiResponse<OAuthLoginResponse> apiResponse = ApiResponse.success(
                HttpStatus.OK.value(),
                "OAuth 인증에 성공했습니다.",
                new OAuthLoginResponse(token.getCredentials(), isFirstLogin)
        );

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
