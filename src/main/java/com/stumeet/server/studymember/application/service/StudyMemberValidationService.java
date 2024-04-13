package com.stumeet.server.studymember.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.out.StudyMemberValidationPort;
import com.stumeet.server.studymember.domain.exception.AlreadyStudyJoinMemberException;
import com.stumeet.server.studymember.domain.exception.NotStudyAdminException;
import com.stumeet.server.studymember.domain.exception.StudyMemberNotJoinedException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyMemberValidationService implements StudyMemberValidationUseCase {

    private final StudyMemberValidationPort studyMemberValidationPort;

    @Override
    public void checkStudyJoinMember(Long studyId, Long memberId) {
        if (studyMemberValidationPort.isNotStudyJoinMember(studyId, memberId)) {
            throw new StudyMemberNotJoinedException(studyId, memberId);
        }
    }

    @Override
    public void checkAlreadyStudyJoinMember(Long studyId, Long memberId) {
        if (studyMemberValidationPort.isAlreadyStudyJoinMember(studyId, memberId)) {
            throw new AlreadyStudyJoinMemberException(studyId, memberId);
        }
    }

    @Override
    public void checkAdmin(Long studyId, Long adminId) {
        if (studyMemberValidationPort.isNotAdmin(studyId, adminId)) {
            throw new NotStudyAdminException(studyId, adminId);
        }
    }
}
