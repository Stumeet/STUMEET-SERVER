package com.stumeet.server.activity.application.port.in;

import com.stumeet.server.activity.adapter.in.response.ActivityParticipantStatusResponses;

public interface ActivityParticipantQueryUseCase {
    ActivityParticipantStatusResponses findAllByActivityId(Long studyId, Long activityId);
}
