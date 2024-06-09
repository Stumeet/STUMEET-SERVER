package com.stumeet.server.activity.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityCategory;

public interface ActivityQueryPort {
    Activity getById(Long activityId);

    Page<Activity> getDetailPagesByCondition(Pageable pageable, Boolean isNotice, Long studyId, ActivityCategory category);
}
