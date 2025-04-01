package com.stumeet.server.studymember.application.port.out;

public interface StudyMemberValidationPort {
    boolean isExist(Long id);

    boolean isNotStudyJoinMember(Long studyId, Long memberId);

    boolean isAlreadyStudyJoinMember(Long studyId, Long memberId);

    boolean isAdmin(Long studyId, Long adminId);
}
