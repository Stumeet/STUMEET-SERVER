package com.stumeet.server.activity.application.port.in;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponse;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.activity.domain.model.ActivitySort;

public interface ActivityQuery {
    Activity getById(Long activityId);

    Page<Activity> getDetailsByCondition(Pageable pageable, Boolean isNotice, Long studyId, List<ActivityCategory> categories, ActivitySort sort);

    Page<ActivityListBriefResponse> getPaginatedBriefsByCondition(Pageable pageable, Boolean isNotice, Long memberId, Long studyId, List<ActivityCategory> categories, LocalDateTime startDate, LocalDateTime endDate, ActivitySort sort);

    List<ActivityListBriefResponse> getBriefsByCondition(Boolean isNotice, Long memberId, Long studyId, List<ActivityCategory> categories, LocalDateTime startDate, LocalDateTime endDate, ActivitySort sort);
}
