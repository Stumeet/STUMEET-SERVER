package com.stumeet.server.activity.application.port.in;

import com.stumeet.server.activity.application.port.in.command.ParticipantStatusUpdateCommand;

public interface ParticipantStatusUpdateUseCase {

    void updateStatus(ParticipantStatusUpdateCommand command);
}
