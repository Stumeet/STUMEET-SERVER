package com.stumeet.server.activity.adapter.in.response;

import lombok.Builder;

public record ActivityParticipantStatusResponse(
        Long id,
        String name,
        String profileImageUrl,
        String status
) {
    @Builder
    public ActivityParticipantStatusResponse {

    }
}
