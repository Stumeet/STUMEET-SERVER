package com.stumeet.server.activity.application.port.out;

import com.stumeet.server.activity.domain.model.ActivityParticipant;

import java.util.List;

public interface ActivityParticipantQueryPort {
    List<ActivityParticipant> findAllByActivityId(Long activityId);
}
