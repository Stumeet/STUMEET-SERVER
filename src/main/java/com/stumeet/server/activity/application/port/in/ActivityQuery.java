package com.stumeet.server.activity.application.port.in;

import com.stumeet.server.activity.domain.model.Activity;

public interface ActivityQuery {
    Activity getById(Long activityId);
}
