package com.stumeet.server.activity.application.port.in;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponse;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityCategory;

public interface ActivityQuery {
    Activity getById(Long activityId);

    Page<Activity> getDetailsByCondition(Pageable pageable, Boolean isNotice, Long studyId, ActivityCategory category);

    Page<ActivityListBriefResponse> getPaginatedBriefsByCondition(Pageable pageable, Boolean isNotice, Long memberId, Long studyId, ActivityCategory category, LocalDateTime startDate, LocalDateTime endDate);

    List<ActivityListBriefResponse> getBriefsByCondition(Boolean isNotice, Long memberId, Long studyId, ActivityCategory category, LocalDateTime startDate, LocalDateTime endDate);
}
