package com.stumeet.server.studymember.application.port.out;

public interface StudyMemberValidationPort {
    boolean isNotStudyJoinMember(Long studyId, Long memberId);

    boolean isAlreadyStudyJoinMember(Long studyId, Long memberId);

    boolean isNotAdmin(Long studyId, Long adminId);
}
