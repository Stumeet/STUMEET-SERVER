package com.stumeet.server.study.application.port.in;

public interface ActivityImageDeleteUseCase {

    void deleteByActivityId(Long studyId, Long activityId);
}
