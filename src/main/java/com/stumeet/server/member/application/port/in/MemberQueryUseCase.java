package com.stumeet.server.member.application.port.in;

import com.stumeet.server.member.domain.Member;

public interface MemberQueryUseCase {

    Member getById(Long id);
}
