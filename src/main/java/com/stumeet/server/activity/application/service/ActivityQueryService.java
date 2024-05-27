package com.stumeet.server.activity.application.service;

import com.stumeet.server.activity.application.port.in.ActivityQuery;
import com.stumeet.server.activity.application.port.out.ActivityQueryPort;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityQueryService implements ActivityQuery {

    private final ActivityQueryPort activityQueryPort;

    @Override
    public Activity getById(Long activityId) {
        return activityQueryPort.getById(activityId);
    }
}
