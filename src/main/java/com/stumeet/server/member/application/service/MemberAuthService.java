package com.stumeet.server.member.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.common.token.service.RefreshTokenService;
import com.stumeet.server.member.adapter.in.web.response.TokenResponse;
import com.stumeet.server.member.application.port.in.MemberSignupCommand;
import com.stumeet.server.member.application.port.in.MemberAuthUseCase;
import com.stumeet.server.member.application.port.in.TokenRenewCommand;
import com.stumeet.server.member.application.port.out.MemberCommandPort;
import com.stumeet.server.member.application.port.out.MemberQueryPort;
import com.stumeet.server.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class MemberAuthService implements MemberAuthUseCase {

    private final MemberCommandPort memberCommandPort;
    private final MemberQueryPort memberQueryPort;
    private final RefreshTokenService refreshTokenService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void signup(Member member, MemberSignupCommand request) {
        member.registerWithAdditionalDetails(request);

        memberCommandPort.save(member);
    }

    @Override
    public TokenResponse renewAccessToken(TokenRenewCommand request) {
        String savedRefreshToken = refreshTokenService.getRefreshToken(request.accessToken(), request.refreshToken());
        refreshTokenService.deleteByAccessToken(request.accessToken());

        Long id = Long.parseLong(jwtTokenProvider.getSubject(request.accessToken()));
        Member member = memberQueryPort.getById(id);
        String renewAccessToken = jwtTokenProvider.generateAccessToken(new LoginMember(member));
        refreshTokenService.save(renewAccessToken, savedRefreshToken);

        return new TokenResponse(renewAccessToken);
    }

}
