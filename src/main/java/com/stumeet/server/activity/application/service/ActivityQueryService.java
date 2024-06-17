package com.stumeet.server.activity.application.service;

import java.time.LocalDateTime;
import java.util.List;

import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponse;
import com.stumeet.server.activity.application.port.in.ActivityQuery;
import com.stumeet.server.activity.application.port.out.ActivityQueryPort;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.common.annotation.UseCase;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityQueryService implements ActivityQuery {

    private final ActivityQueryPort activityQueryPort;

    @Override
    public Activity getById(Long activityId) {
        return activityQueryPort.getById(activityId);
    }

    @Override
    public Page<Activity> getDetailsByCondition(
            Pageable pageable,
            Boolean isNotice,
            Long studyId,
            ActivityCategory category) {
        return activityQueryPort.getDetailPagesByCondition(pageable, isNotice, studyId, category);
    }

    @Override
    public Page<ActivityListBriefResponse> getPaginatedBriefsByCondition(
            Pageable pageable,
            Boolean isNotice,
            Long memberId,
            Long studyId,
            ActivityCategory category,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return activityQueryPort.getPaginatedBriefsByCondition(pageable, isNotice, memberId, studyId, category,
                startDate, endDate);
    }

    @Override
    public List<ActivityListBriefResponse> getBriefsByCondition(
            Boolean isNotice,
            Long memberId,
            Long studyId,
            ActivityCategory category,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return activityQueryPort.getBriefsByCondition(isNotice, memberId, studyId, category, startDate, endDate);
    }
}
