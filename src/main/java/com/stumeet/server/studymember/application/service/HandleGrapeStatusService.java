package com.stumeet.server.studymember.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.studymember.application.port.in.HandleGrapeStatusUseCase;
import com.stumeet.server.studymember.application.port.out.StudyMemberQueryPort;
import com.stumeet.server.studymember.application.port.out.StudyMemberUpdatePort;
import com.stumeet.server.studymember.domain.StudyMember;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class HandleGrapeStatusService implements HandleGrapeStatusUseCase {
    private final StudyMemberQueryPort studyMemberQueryPort;
    private final StudyMemberUpdatePort studyMemberUpdatePort;

    @Override
    public void resetGrapeStatus(Long studyId, Long memberId) {
        StudyMember studyMember = studyMemberQueryPort.findStudyMember(studyId, memberId);
        studyMember.resetGrapeStatus();
        studyMemberUpdatePort.update(studyMember);
    }

    @Override
    public void markGrapeSent(Long studyMemberId) {
        StudyMember studyMember = studyMemberQueryPort.findStudyMember(studyMemberId);
        studyMember.markGrapeSent();
        studyMemberUpdatePort.update(studyMember);
    }
}
