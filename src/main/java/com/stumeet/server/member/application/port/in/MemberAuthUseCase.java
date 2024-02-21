package com.stumeet.server.member.application.port.in;

import com.stumeet.server.member.adapter.in.web.response.TokenResponse;
import com.stumeet.server.member.domain.Member;

public interface MemberAuthUseCase {

    void signup(Member member, MemberSignupCommand request);

    TokenResponse renewAccessToken(TokenRenewCommand request);
}
