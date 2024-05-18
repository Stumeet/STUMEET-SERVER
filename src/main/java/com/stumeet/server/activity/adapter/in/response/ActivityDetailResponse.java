package com.stumeet.server.activity.adapter.in.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record ActivityDetailResponse(
        Long id,
        String title,
        String content,
        List<ActivityImageResponse> imageUrl,
        ActivityParticipantResponse author,
        List<ActivityParticipantResponse> participants,
        String status,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime createdAt

) {

    @QueryProjection
    @Builder
    public ActivityDetailResponse {
    }
}
