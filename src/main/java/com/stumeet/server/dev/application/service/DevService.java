package com.stumeet.server.dev.application.service;

import org.springframework.stereotype.Service;

import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.dev.adapter.in.web.response.AccessTokenResponse;
import com.stumeet.server.member.application.port.in.MemberQueryUseCase;
import com.stumeet.server.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DevService {

	private final JwtTokenProvider jwtTokenProvider;
	private final MemberQueryUseCase memberQueryUseCase;

	public AccessTokenResponse getAccessToken(Long memberId) {
		Member member = memberQueryUseCase.getById(memberId);
		LoginMember loginMember = new LoginMember(member);

		return new AccessTokenResponse(jwtTokenProvider.generateAccessToken(loginMember));
	}
}
