package com.stumeet.server.studymember.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberResponses;
import com.stumeet.server.studymember.application.port.in.StudyMemberQueryUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.out.StudyMemberQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyMemberQueryService implements StudyMemberQueryUseCase {

    private final StudyMemberValidationUseCase studyMemberValidationUseCase;
    private final StudyMemberQueryPort studyMemberQueryPort;

    @Override
    public StudyMemberResponses getStudyMembers(Long studyId, Long memberId) {
        studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);
        List<SimpleStudyMemberResponse> response = studyMemberQueryPort.findStudyMembers(studyId);

        return new StudyMemberResponses(response);
    }
}
