package com.stumeet.server.activity.application.port.out;

import com.stumeet.server.activity.domain.model.ActivityParticipant;

import java.util.List;

public interface ActivityParticipantQueryPort {

    ActivityParticipant findByIdAndActivityId(Long id, Long activityId);

    List<ActivityParticipant> findAllByActivityId(Long activityId);

    List<ActivityParticipant> findMemberParticipation(Long studyId, Long memberId);
}
