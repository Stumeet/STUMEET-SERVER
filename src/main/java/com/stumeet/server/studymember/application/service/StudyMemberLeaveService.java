package com.stumeet.server.studymember.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberLeaveUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.out.StudyMemberLeavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class StudyMemberLeaveService implements StudyMemberLeaveUseCase {
    private final StudyValidationUseCase studyValidationUseCase;
    private final StudyMemberValidationUseCase studyMemberValidationUseCase;
    private final StudyMemberLeavePort studyMemberLeavePort;


    @Override
    public void leave(Long studyId, Long memberId) {
        studyValidationUseCase.checkById(studyId);
        studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);

        studyMemberLeavePort.leave(studyId, memberId);
    }

    @Override
    public void kick(Long studyId, Long kickMemberId, Long loginMemberId) {
        studyValidationUseCase.checkById(studyId);
        studyMemberValidationUseCase.checkAdmin(studyId, loginMemberId);
        studyMemberValidationUseCase.checkStudyJoinMember(studyId, kickMemberId);

        studyMemberLeavePort.leave(studyId, kickMemberId);
    }
}
