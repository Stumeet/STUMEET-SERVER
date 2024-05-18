package com.stumeet.server.activity.adapter.in.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

public record ActivityParticipantResponse(
        Long id,
        String name,
        String profileImageUrl
) {

    @QueryProjection
    @Builder
    public ActivityParticipantResponse {
    }
}
