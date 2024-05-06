package com.stumeet.server.activity.application.port.in;

import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;

public interface ActivityCreateUseCase {
    void create(Long studyId, ActivityCreateCommand command, Long id);
}
