package com.stumeet.server.activity.application.port.out;

import com.stumeet.server.activity.domain.model.ActivityImage;

import java.util.List;

public interface ActivityImageQueryPort {
    List<ActivityImage> findAllByActivityId(Long activityId);
}
