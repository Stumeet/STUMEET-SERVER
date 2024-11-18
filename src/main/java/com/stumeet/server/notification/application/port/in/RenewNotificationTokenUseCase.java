package com.stumeet.server.notification.application.port.in;

import com.stumeet.server.notification.application.port.in.command.RenewNotificationTokenCommand;

public interface RenewNotificationTokenUseCase {
    void renewNotificationToken(RenewNotificationTokenCommand command);
}
