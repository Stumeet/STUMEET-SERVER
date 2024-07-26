package com.stumeet.server.activity.application.port.out;

import java.util.List;

import com.stumeet.server.activity.domain.model.ActivityImage;

public interface ActivityImageCommandPort {

    void deleteAllByActivityId(Long studyId, Long activityId);

    void update(Long activityId, List<ActivityImage> activityImages);
}
