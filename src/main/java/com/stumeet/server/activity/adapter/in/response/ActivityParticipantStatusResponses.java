package com.stumeet.server.activity.adapter.in.response;

import lombok.Builder;

import java.util.List;

public record ActivityParticipantStatusResponses(
        List<ActivityParticipantStatusResponse> participants
) {

    @Builder
    public ActivityParticipantStatusResponses {
    }
}