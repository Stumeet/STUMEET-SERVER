package com.stumeet.server.activity.application.service.model;

import com.stumeet.server.activity.domain.model.ActivityCategory;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ActivitySource(
        Long id,
        ActivityLinkedStudyCreateSource study,
        ActivityMemberCreateSource author,
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
    @Builder
    public record ActivityMemberCreateSource(
        Long id,
        String name,
        String image
    ) {
    }

    @Builder
    public record ActivityLinkedStudyCreateSource(
        Long id,
        String name
    ) {
    }
}
