package com.stumeet.server.activity.application.service;

import com.stumeet.server.activity.application.port.in.ActivityImageQuery;
import com.stumeet.server.activity.application.port.out.ActivityImageQueryPort;
import com.stumeet.server.activity.domain.model.ActivityImage;
import com.stumeet.server.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityImageQueryService implements ActivityImageQuery {
    private final ActivityImageQueryPort activityImageQueryPort;


    @Override
    public List<ActivityImage> findAllByActivityId(Long activityId) {
        return activityImageQueryPort.findAllByActivityId(activityId);
    }
}
