package com.stumeet.server.studymember.application.port.in;

public interface StudyMemberValidationUseCase {
    void checkStudyJoinMember(Long studyId, Long memberId);

    void checkAlreadyStudyJoinMember(Long studyId, Long memberId);

    void checkAdmin(Long studyId, Long adminId);
}
