package com.stumeet.server.activity.application.port.in;

import com.stumeet.server.activity.application.port.in.command.ActivityDeleteCommand;

public interface ActivityDeleteUseCase {

    void delete(ActivityDeleteCommand command);
}
