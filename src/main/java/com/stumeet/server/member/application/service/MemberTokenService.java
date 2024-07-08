package com.stumeet.server.member.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.common.token.service.JwtTokenService;
import com.stumeet.server.member.adapter.in.web.response.TokenResponse;
import com.stumeet.server.member.application.port.in.*;
import com.stumeet.server.member.application.port.in.command.TokenRenewCommand;
import com.stumeet.server.member.application.port.out.MemberQueryPort;
import com.stumeet.server.member.domain.Member;

import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class MemberTokenService implements MemberTokenUseCase {

	private final MemberQueryPort memberQueryPort;
	private final JwtTokenService jwtTokenService;
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public TokenResponse renewTokens(TokenRenewCommand request) {
		jwtTokenService.validateRefreshToken(request.accessToken(), request.refreshToken());
		jwtTokenService.deleteUsedTokenByAccessToken(request.accessToken());

		Long id = Long.parseLong(jwtTokenProvider.getSubject(request.refreshToken()));
		Member member = memberQueryPort.getById(id);

		String renewAccessToken = jwtTokenProvider.generateAccessToken(new LoginMember(member));
		String renewRefreshToken = jwtTokenProvider.generateRefreshToken(id);

		jwtTokenService.saveNewTokens(renewAccessToken, renewRefreshToken);
		jwtTokenService.addToBlackList(request.refreshToken(), renewAccessToken);

		return new TokenResponse(renewAccessToken, renewRefreshToken);
	}
}
