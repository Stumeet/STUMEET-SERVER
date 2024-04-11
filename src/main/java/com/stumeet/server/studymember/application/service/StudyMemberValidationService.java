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
            throw new StudyMemberNotJoinedException("스터디에 가입된 멤버가 아닙니다. 전달받은 studyId=" + studyId + ", memberId=" + memberId);
        }
    }

    @Override
    public void checkAlreadyStudyJoinMember(Long studyId, Long memberId) {
        if (studyMemberValidationPort.isAlreadyStudyJoinMember(studyId, memberId)) {
            throw new AlreadyStudyJoinMemberException("스터디에 이미 가입한 멤버입니다. 전달받은 studyId=" + studyId + ", memberId=" + memberId);
        }
    }

    @Override
    public void checkAdmin(Long studyId, Long adminId) {
        if (studyMemberValidationPort.isNotAdmin(studyId, adminId)) {
            throw new NotStudyAdminException("스터디 관리자가 아닙니다. 전달받은 studyId=" + studyId + ", adminId=" + adminId);
        }
    }
}
