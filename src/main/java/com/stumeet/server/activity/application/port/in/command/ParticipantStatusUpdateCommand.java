package com.stumeet.server.activity.application.port.in.command;

import com.stumeet.server.activity.domain.model.ActivityStatus;

import lombok.Builder;

public record ParticipantStatusUpdateCommand(
        Long adminId,
        Long studyId,
        Long activityId,
        Long memberId,
        Long participantId,
        ActivityStatus status
) {

    @Builder
    public ParticipantStatusUpdateCommand(Long adminId, Long studyId, Long activityId, Long memberId, Long participantId, String status) {
        this(adminId, studyId, activityId, memberId, participantId, ActivityStatus.findByStatus(status));
    }
}
