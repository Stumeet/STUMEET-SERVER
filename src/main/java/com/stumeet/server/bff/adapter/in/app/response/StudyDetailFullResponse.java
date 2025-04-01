package com.stumeet.server.bff.adapter.in.app.response;

import com.stumeet.server.activity.adapter.in.response.ActivityListDetailedPageResponse;
import com.stumeet.server.study.adapter.in.web.response.StudyDetailResponse;

public record StudyDetailFullResponse(
        StudyDetailResponse studyDetail,
        ActivityListDetailedPageResponse activityNotice,
        Boolean isAdmin,
        Boolean canSendGrape
) {
}
