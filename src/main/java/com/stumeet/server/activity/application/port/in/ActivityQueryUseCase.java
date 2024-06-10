package com.stumeet.server.activity.application.port.in;

import com.stumeet.server.activity.adapter.in.response.ActivityDetailResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityListDetailedPageResponses;
import com.stumeet.server.activity.application.port.in.query.ActivityListDetailedQuery;

public interface ActivityQueryUseCase {
    ActivityDetailResponse getById(Long studyId, Long activityId, Long memberId);

    ActivityListDetailedPageResponses getDetails(ActivityListDetailedQuery query);
}
