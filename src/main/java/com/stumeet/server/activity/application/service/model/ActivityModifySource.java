package com.stumeet.server.activity.application.service.model;

import java.time.LocalDateTime;

import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.activity.domain.model.ActivityMember;

import lombok.Builder;

@Builder
public record ActivityModifySource(
        Long id,
        Long studyId,
        ActivityMember author,
        ActivityCategory category,
        String title,
        String content,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String location,
        String link,
        boolean isNotice,
        LocalDateTime createdAt
) {
}
