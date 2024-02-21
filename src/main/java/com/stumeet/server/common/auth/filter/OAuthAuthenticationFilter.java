package com.stumeet.server.common.auth.filter;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.common.auth.token.StumeetAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class OAuthAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public OAuthAuthenticationFilter(
            AuthenticationFailureHandler failureHandler,
            AuthenticationManager authenticationManager,
            AuthenticationSuccessHandler authenticationSuccessHandler
    ) {
        super("/api/v1/oauth");
        setAuthenticationFailureHandler(failureHandler);
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(authenticationSuccessHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String accessToken = request.getHeader(AuthenticationHeader.ACCESS_TOKEN.getName());
        String provider = request.getHeader(AuthenticationHeader.X_OAUTH_PROVIDER.getName());

        checkAuthorizationInfo(accessToken, provider);

        return getAuthenticationManager().authenticate(StumeetAuthenticationToken.unAuthenticate(accessToken, provider));
    }


    private void checkAuthorizationInfo(String accessToken, String provider) {
        if (accessToken == null || provider == null) {
            throw new BadCredentialsException("잘못된 인증 정보가 전달되었습니다. 확인해주세요");
        }
    }
}
