package com.stumeet.server.studymember.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.studymember.application.port.in.LegacyStudyHideUseCase;
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

    private final StudyMemberQueryPort studyMemberQueryPort;
    private final StudyMemberValidationPort studyMemberValidationPort;
    private final StudyMemberUpdatePort studyMemberUpdatePort;

    @Override
    public void hideLegacyStudyForMember(Long studyId, Long memberId) {
        studyMemberValidationPort.isNotStudyJoinMember(studyId, memberId);
        studyValidationUseCase.checkLegacyStudy(studyId);

        StudyMember studyMember = studyMemberQueryPort.findStudyMember(studyId, memberId);
        studyMember.hideLegacyStudy();

        studyMemberUpdatePort.update(studyMember);
    }
}
