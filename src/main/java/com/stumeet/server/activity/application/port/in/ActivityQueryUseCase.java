package com.stumeet.server.activity.application.port.in;

import com.stumeet.server.activity.adapter.in.response.ActivityDetailResponse;

public interface ActivityQueryUseCase {
    ActivityDetailResponse getById(Long studyId, Long activityId, Long memberId);
}
