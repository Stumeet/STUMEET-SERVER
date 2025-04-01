package com.stumeet.server.activity.adapter.in.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record ActivityDetailResponse(
        Long id,
        String category,
        String title,
        String content,
        List<ActivityImageResponse> imageUrl,
        ActivityParticipantSimpleResponse author,
        List<ActivityParticipantSimpleResponse> participants,
        String status,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String location,
        String link,
        LocalDateTime createdAt,
        boolean isAuthor,
        boolean isAdmin
) {

    @QueryProjection
    @Builder
    public ActivityDetailResponse {
    }
}
