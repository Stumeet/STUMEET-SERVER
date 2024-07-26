package com.stumeet.server.activity.application.port.in;

import com.stumeet.server.activity.application.port.in.command.ActivityModifyCommand;

public interface ActivityModifyUseCase {

    void modify(Long memberId, Long studyId, Long activityId, ActivityModifyCommand command);
}
