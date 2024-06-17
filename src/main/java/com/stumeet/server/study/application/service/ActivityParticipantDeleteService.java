package com.stumeet.server.study.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.activity.application.port.out.ActivityParticipantCommandPort;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.ActivityParticipantDeleteUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ActivityParticipantDeleteService implements ActivityParticipantDeleteUseCase {

    private final ActivityParticipantCommandPort activityParticipantCommandPort;

    @Override
    public void deleteByActivityId(Long activityId) {
        activityParticipantCommandPort.deleteByActivityId(activityId);
    }
}
