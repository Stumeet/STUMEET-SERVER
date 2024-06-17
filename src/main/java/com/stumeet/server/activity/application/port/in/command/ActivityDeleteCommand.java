package com.stumeet.server.activity.application.port.in.command;

import lombok.Builder;

@Builder
public record ActivityDeleteCommand(
        Long memberId,
        Long studyId,
        Long activityId
) {
}
