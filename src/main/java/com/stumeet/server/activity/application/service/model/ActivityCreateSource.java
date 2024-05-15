package com.stumeet.server.activity.application.service.model;

import com.stumeet.server.activity.domain.model.ActivityCategory;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ActivityCreateSource(
        Long id,
        Long studyId,
        ActivityMemberCreateSource author,
        ActivityCategory category,
        String title,
        String content,
        boolean isNotice,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime createdAt,
        String location
) {
    @Builder
    public record ActivityMemberCreateSource(
        Long id,
        String name,
        String image
    ) {
    }
}
