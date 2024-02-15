package com.stumeet.server.common.auth.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.auth.token.StumeetAuthenticationToken;
import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.member.application.port.in.MemberQueryUseCase;
import com.stumeet.server.member.domain.Member;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JwtAuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberQueryUseCase memberQueryUseCase;

    public StumeetAuthenticationToken getAuthentication(String accessToken) {
        Claims claims = jwtTokenProvider.getClaims(accessToken);
        String id = claims.getSubject();

        Member member = memberQueryUseCase.getById(Long.parseLong(id));
        List<SimpleGrantedAuthority> role = List.of(new SimpleGrantedAuthority(member.getRole().toString()));

        return StumeetAuthenticationToken.createAuthenticationJwtToken(
                role,
                accessToken,
                new LoginMember(member)
        );
    }
}
