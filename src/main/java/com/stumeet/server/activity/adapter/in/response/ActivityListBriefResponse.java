package com.stumeet.server.activity.adapter.in.response;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;
import com.stumeet.server.activity.domain.model.ActivityStatus;

import lombok.Builder;

@Builder
public record ActivityListBriefResponse(
        Long id,
        Long studyId,
        String studyName,
        String category,
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String location,
        ActivityStatus status,
        LocalDateTime createdAt
) {
    @QueryProjection
    public ActivityListBriefResponse {
    }
}
