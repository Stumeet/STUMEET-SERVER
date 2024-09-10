package com.stumeet.server.studymember.application.service;

import com.stumeet.server.activity.application.port.in.EvaluateMemberAchievementUseCase;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberDetailResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberResponses;
import com.stumeet.server.studymember.application.port.in.StudyMemberQueryUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.out.StudyMemberQueryPort;
import com.stumeet.server.studymember.domain.StudyMember;

import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyMemberQueryService implements StudyMemberQueryUseCase {

    private final StudyValidationUseCase studyValidationUseCase;
    private final StudyMemberValidationUseCase studyMemberValidationUseCase;
    private final EvaluateMemberAchievementUseCase evaluateMemberAchievementUseCase;

    private final StudyMemberQueryPort studyMemberQueryPort;

    @Override
    public StudyMemberResponses getStudyMembers(Long studyId, Long requesterId) {
        studyValidationUseCase.checkById(studyId);
        studyMemberValidationUseCase.checkStudyJoinMember(studyId, requesterId);
        List<SimpleStudyMemberResponse> response = studyMemberQueryPort.findStudyMembers(studyId);

        return new StudyMemberResponses(response);
    }

    @Override
    public StudyMemberDetailResponse getStudyMemberDetail(Long studyId, Long targetMemberId, Long requesterId) {
        studyValidationUseCase.checkById(studyId);
        studyMemberValidationUseCase.checkStudyJoinMember(studyId, requesterId);

        StudyMember studyMember = studyMemberQueryPort.findStudyMember(studyId, targetMemberId);
        Integer achievement = evaluateMemberAchievementUseCase.getMemberAchievement(studyId, targetMemberId);

        return new StudyMemberDetailResponse(
                studyMember.getMember().getId(),
                studyMember.getMember().getName(),
                studyMember.getMember().getImage(),
                studyMember.getMember().getRegion(),
                studyMember.getMember().getProfession().getName(),
                studyMember.isSentGrape(),
                achievement
        );
    }
}
