package com.stumeet.server.member.application.port.in;

import com.stumeet.server.member.domain.Member;

public interface MemberSignupUseCase {

    void signup(Member member, MemberSignupCommand request);
}
