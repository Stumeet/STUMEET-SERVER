package com.stumeet.server.activity.application.port.in;

import com.stumeet.server.activity.application.port.in.command.ActivityUpdateCommand;

public interface ActivityUpdateUseCase {

    void update(Long memberId, Long studyId, Long activityId, ActivityUpdateCommand command);
}
