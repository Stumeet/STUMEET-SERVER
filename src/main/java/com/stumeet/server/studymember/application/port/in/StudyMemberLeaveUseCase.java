package com.stumeet.server.studymember.application.port.in;

public interface StudyMemberLeaveUseCase {
    void leave(Long studyId, Long memberId);
}