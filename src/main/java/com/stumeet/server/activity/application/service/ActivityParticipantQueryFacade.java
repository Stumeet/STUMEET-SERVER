package com.stumeet.server.activity.application.service;

import com.stumeet.server.activity.adapter.in.response.ActivityParticipantStatusResponses;
import com.stumeet.server.activity.application.port.in.ActivityParticipantQuery;
import com.stumeet.server.activity.application.port.in.ActivityParticipantQueryUseCase;
import com.stumeet.server.activity.application.port.in.ActivityQuery;
import com.stumeet.server.activity.application.port.in.mapper.ActivityParticipantUseCaseMapper;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityParticipantQueryFacade implements ActivityParticipantQueryUseCase {

    private final ActivityParticipantQuery activityParticipantQuery;
    private final StudyValidationUseCase studyValidationUseCase;

    private final ActivityQuery activityQuery;

    private final ActivityParticipantUseCaseMapper activityParticipantUseCaseMapper;

    @Override
    public ActivityParticipantStatusResponses findAllByActivityId(Long studyId, Long activityId) {
        studyValidationUseCase.checkById(studyId);
        activityQuery.getById(activityId);

        List<ActivityParticipant> participants = activityParticipantQuery.findAllByActivityId(activityId);

        return activityParticipantUseCaseMapper.toStatusResponses(participants);
    }
}
