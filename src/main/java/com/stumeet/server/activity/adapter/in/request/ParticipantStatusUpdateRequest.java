package com.stumeet.server.activity.adapter.in.request;

public record ParticipantStatusUpdateRequest(
        Long participantId,
        String status
) {
}
