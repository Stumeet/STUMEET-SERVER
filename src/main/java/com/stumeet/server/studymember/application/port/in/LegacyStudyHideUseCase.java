package com.stumeet.server.studymember.application.port.in;

public interface LegacyStudyHideUseCase {

    void hideLegacyStudyForMember(Long studyId, Long member);
}
