package com.stumeet.server.member.application.port.in;

public interface MemberLevelUseCase {

    void progress(long memberId, int xp);
}
