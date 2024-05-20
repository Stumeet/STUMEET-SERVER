package com.stumeet.server.activity.adapter.in.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

public record ActivityParticipantSimpleResponse(
        Long memberId,
        String name,
        String profileImageUrl
) {

    @QueryProjection
    @Builder
    public ActivityParticipantSimpleResponse {
    }
}
