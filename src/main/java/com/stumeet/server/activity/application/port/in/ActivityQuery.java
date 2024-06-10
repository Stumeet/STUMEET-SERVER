package com.stumeet.server.activity.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityCategory;

public interface ActivityQuery {
    Activity getById(Long activityId);

    Page<Activity> getDetailsByCondition(Pageable pageable, Boolean isNotice, Long studyId, ActivityCategory category);
}
