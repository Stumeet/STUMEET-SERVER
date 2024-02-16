package com.stumeet.server.member.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.member.application.port.in.MemberSignupCommand;
import com.stumeet.server.member.application.port.in.MemberSignupUseCase;
import com.stumeet.server.member.application.port.out.MemberCommandPort;
import com.stumeet.server.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class MemberSignupService implements MemberSignupUseCase {

    private final MemberCommandPort memberCommandPort;

    @Override
    public void signup(Member member, MemberSignupCommand request) {
        member.registerWithAdditionalDetails(request);

        memberCommandPort.save(member);
    }

}
