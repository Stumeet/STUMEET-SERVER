package com.stumeet.server.member.application.port.in;

import com.stumeet.server.member.application.port.in.command.MemberSignupCommand;
import com.stumeet.server.member.application.port.in.command.MemberUpdateCommand;
import com.stumeet.server.member.domain.Member;

public interface MemberProfileUseCase {
    void signup(Member member, MemberSignupCommand request);

    void updateProfile(Member member, MemberUpdateCommand request);
}
