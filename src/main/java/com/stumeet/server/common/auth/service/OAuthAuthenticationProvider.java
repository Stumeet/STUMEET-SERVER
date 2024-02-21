package com.stumeet.server.common.auth.service;

import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.auth.token.StumeetAuthenticationToken;
import com.stumeet.server.common.client.oauth.OAuthClient;
import com.stumeet.server.common.client.oauth.model.OAuthUserProfileResponse;
import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.common.token.service.RefreshTokenService;
import com.stumeet.server.member.application.port.in.MemberOAuthUseCase;
import com.stumeet.server.member.domain.Member;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthAuthenticationProvider implements AuthenticationProvider {

    private final Map<String, OAuthClient> oAuthClient;
    private final MemberOAuthUseCase memberOAuthUseCase;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        StumeetAuthenticationToken token = (StumeetAuthenticationToken) authentication;
        String providerAccessToken = token.getCredentials();
        String provider = token.getProvider();

        try {
            OAuthUserProfileResponse myProfile = oAuthClient.get(provider).getUserId(providerAccessToken);
            Member member = memberOAuthUseCase.getMemberOrCreate(myProfile, provider);
            LoginMember loginMember = new LoginMember(member);

            String accessToken = jwtTokenProvider.generateAccessToken(loginMember);
            String refreshToken = jwtTokenProvider.generateRefreshToken();
            refreshTokenService.save(accessToken, refreshToken);

            return StumeetAuthenticationToken.authenticateOAuth(
                    loginMember.getAuthorities(),
                    accessToken,
                    refreshToken,
                    provider,
                    loginMember
            );
        } catch (FeignException e) {
            throw new BadCredentialsException("OAuth 인증에 실패했습니다.", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return StumeetAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
