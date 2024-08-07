package com.stumeet.server.stub;

import com.stumeet.server.activity.adapter.in.request.ParticipantStatusUpdateRequest;

public class ActivityParticipantStub {

    public static ParticipantStatusUpdateRequest getParticipantStatusUpdateRequest() {
        return ParticipantStatusUpdateRequest.builder()
                .participantId(7L)
                .status("ABSENCE")
                .build();
    }
}
