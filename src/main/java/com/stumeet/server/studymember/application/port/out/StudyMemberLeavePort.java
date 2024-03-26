package com.stumeet.server.studymember.application.port.out;

public interface StudyMemberLeavePort {
    void leave(Long studyId, Long memberId);
}
