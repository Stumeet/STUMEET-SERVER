package com.stumeet.server.activity.application.port.out;

public interface ActivityImageCommandPort {

    void deleteAllByActivityId(Long studyId, Long activityId);
}
