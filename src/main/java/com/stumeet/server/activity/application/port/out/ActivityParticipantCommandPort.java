package com.stumeet.server.activity.application.port.out;

import java.util.List;

import com.stumeet.server.activity.domain.model.ActivityParticipant;

public interface ActivityParticipantCommandPort {

    void update(ActivityParticipant participant);

    void deleteByActivityId(Long activityId);

    void updateActivityParticipants(Long activityId, List<ActivityParticipant> participants);
}
