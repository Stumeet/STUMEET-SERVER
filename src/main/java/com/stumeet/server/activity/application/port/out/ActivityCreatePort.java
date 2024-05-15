package com.stumeet.server.activity.application.port.out;

import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import com.stumeet.server.activity.domain.model.Activity;

public interface ActivityCreatePort {
    Activity create(Activity activity);
}
