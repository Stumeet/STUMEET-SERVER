package com.stumeet.server.activity.application.port.in;

import com.stumeet.server.activity.domain.model.ActivityParticipant;

import java.util.List;

public interface ActivityParticipantQuery {
    List<ActivityParticipant> findAllByActivityId(Long activityId);
}
