package com.stumeet.server.study.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.activity.application.port.out.ActivityImageCommandPort;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.ActivityImageDeleteUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ActivityImageDeleteService implements ActivityImageDeleteUseCase {

    private final ActivityImageCommandPort activityImageCommandPort;

    @Override
    public void deleteByActivityId(Long studyId, Long activityId) {
        activityImageCommandPort.deleteAllByActivityId(studyId, activityId);
    }
}
