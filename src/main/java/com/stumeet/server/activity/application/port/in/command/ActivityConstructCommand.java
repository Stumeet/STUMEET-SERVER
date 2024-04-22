package com.stumeet.server.activity.application.port.in.command;

import com.stumeet.server.activity.domain.model.ActivityCategory;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ActivityConstructCommand(
        Long id,
        Long studyId,
        ActivityMemberConstructCommand author,
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
    public record ActivityMemberConstructCommand(
        Long id,
        String name,
        String image
    ) {
    }
}
