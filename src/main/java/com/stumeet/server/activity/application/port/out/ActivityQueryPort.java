package com.stumeet.server.activity.application.port.out;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponse;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.activity.domain.model.ActivitySort;

public interface ActivityQueryPort {
    Activity getById(Long activityId);

    Activity getByStudyIdAndId(Long studyId, Long id);

    Page<Activity> getDetailPagesByCondition(Pageable pageable, Boolean isNotice, Long studyId, List<ActivityCategory> categories, ActivitySort sort);

    Page<ActivityListBriefResponse> getPaginatedBriefsByCondition(Pageable pageable, Boolean isNotice, Long memberId, Long studyId, List<ActivityCategory> categories, LocalDateTime startDate, LocalDateTime endDate, ActivitySort sort);

    List<ActivityListBriefResponse> getBriefsByCondition(Boolean isNotice, Long memberId, Long studyId, List<ActivityCategory> categories, LocalDateTime startDate, LocalDateTime endDate, ActivitySort sort);
}
