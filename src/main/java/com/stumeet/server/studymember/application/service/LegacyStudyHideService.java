package com.stumeet.server.studymember.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.studymember.application.port.in.LegacyStudyHideUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.out.StudyMemberQueryPort;
import com.stumeet.server.studymember.application.port.out.StudyMemberUpdatePort;
import com.stumeet.server.studymember.application.port.out.StudyMemberValidationPort;
import com.stumeet.server.studymember.domain.StudyMember;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class LegacyStudyHideService implements LegacyStudyHideUseCase {

    private final StudyValidationUseCase studyValidationUseCase;
    private final StudyMemberValidationUseCase studyMemberValidationUseCase;

    private final StudyMemberQueryPort studyMemberQueryPort;
    private final StudyMemberUpdatePort studyMemberUpdatePort;

    @Override
    public void hideLegacyStudyForMember(Long studyId, Long memberId) {
        studyValidationUseCase.checkLegacyStudy(studyId);
        studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);

        StudyMember studyMember = studyMemberQueryPort.findStudyMember(studyId, memberId);
        studyMember.hideLegacyStudy();

        studyMemberUpdatePort.update(studyMember);
    }
}
