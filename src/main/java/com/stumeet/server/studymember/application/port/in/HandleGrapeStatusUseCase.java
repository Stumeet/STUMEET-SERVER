package com.stumeet.server.studymember.application.port.in;

public interface HandleGrapeStatusUseCase {
    void resetGrapeStatus(Long studyId, Long memberId);

    void markGrapeSent(Long studyMemberId);
}
