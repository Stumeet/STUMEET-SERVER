package com.stumeet.server.activity.application.port.out;

import com.stumeet.server.activity.domain.model.ActivityParticipant;

import java.util.List;

public interface ActivityParticipantQueryPort {

    ActivityParticipant findByActivityIdAndMemberIdAndId(Long activityId, Long memberId, Long id);

    List<ActivityParticipant> findAllByActivityId(Long activityId);
}
