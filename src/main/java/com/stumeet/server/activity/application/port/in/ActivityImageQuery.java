package com.stumeet.server.activity.application.port.in;

import com.stumeet.server.activity.domain.model.ActivityImage;

import java.util.List;

public interface ActivityImageQuery {
    List<ActivityImage> findAllByActivityId(Long activityId);
}
