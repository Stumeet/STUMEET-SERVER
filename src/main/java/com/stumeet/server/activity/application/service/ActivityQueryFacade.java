package com.stumeet.server.activity.application.service;

import com.stumeet.server.activity.adapter.in.response.ActivityDetailResponse;
import com.stumeet.server.activity.application.port.in.ActivityImageQuery;
import com.stumeet.server.activity.application.port.in.ActivityParticipantQuery;
import com.stumeet.server.activity.application.port.in.ActivityQuery;
import com.stumeet.server.activity.application.port.in.ActivityQueryUseCase;
import com.stumeet.server.activity.application.port.in.mapper.ActivityImageUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityParticipantUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityUseCaseMapper;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityImage;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityQueryFacade implements ActivityQueryUseCase {

    private final StudyValidationUseCase studyValidationUseCase;
    private final StudyMemberValidationUseCase studyMemberValidationUseCase;

    private final ActivityQuery activityQuery;
    private final ActivityImageQuery activityImageQuery;
    private final ActivityParticipantQuery activityParticipantQuery;

    private final ActivityUseCaseMapper activityUseCaseMapper;
    private final ActivityImageUseCaseMapper activityImageUseCaseMapper;
    private final ActivityParticipantUseCaseMapper activityParticipantUseCaseMapper;


    @Override
    public ActivityDetailResponse getById(Long studyId, Long activityId, Long memberId) {
        studyValidationUseCase.checkById(studyId);
        studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);

        Activity activity = activityQuery.getById(activityId);
        List<ActivityImage> activityImages = activityImageQuery.findAllByActivityId(activityId);
        List<ActivityParticipant> participants = activityParticipantQuery.findAllByActivityId(activityId);

        ActivityParticipant me = participants.stream()
                .filter(participant -> participant.getMember().getId().equals(memberId))
                .findAny()
                .orElseGet(ActivityParticipant::makeNotJoinedMember);

        return activityUseCaseMapper.toDetailResponse(
                activity,
                activityImageUseCaseMapper.toResponses(activityImages),
                activityParticipantUseCaseMapper.toResponse(activity.getAuthor()),
                activityParticipantUseCaseMapper.toResponses(participants),
                me.getStatus().getDescription()
        );
    }
}
