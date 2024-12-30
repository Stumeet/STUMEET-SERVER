package com.stumeet.server.studymember.application.port.in;

public interface StudyMemberValidationUseCase {
    void checkById(Long id);

    void checkStudyJoinMember(Long studyId, Long memberId);

    void checkAlreadyStudyJoinMember(Long studyId, Long memberId);

    void checkAdmin(Long studyId, Long adminId);
}
