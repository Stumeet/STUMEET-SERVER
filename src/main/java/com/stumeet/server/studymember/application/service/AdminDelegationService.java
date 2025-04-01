package com.stumeet.server.studymember.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.studymember.application.port.in.AdminDelegationUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.out.AdminManagementPort;

import lombok.RequiredArgsConstructor;

@Transactional
@UseCase
@RequiredArgsConstructor
public class AdminDelegationService implements AdminDelegationUseCase {

    private final StudyMemberValidationUseCase studyMemberValidationUseCase;
    private final AdminManagementPort adminManagementPort;

    @Override
    public void delegateAdmin(Long studyId, Long adminId, Long memberId) {
        studyMemberValidationUseCase.checkAdmin(studyId, adminId);
        studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);

        adminManagementPort.delegateAdmin(studyId, adminId, memberId);
    }
}
