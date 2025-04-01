package com.stumeet.server.activity.adapter.in.request;

import lombok.Builder;

@Builder
public record ParticipantStatusUpdateRequest(
        Long participantId,
        String status
) {
}
