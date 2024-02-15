package com.stumeet.server.member.application.port.out;

import com.stumeet.server.member.domain.Member;

public interface MemberCommandPort {

    Member save(Member member);
}
