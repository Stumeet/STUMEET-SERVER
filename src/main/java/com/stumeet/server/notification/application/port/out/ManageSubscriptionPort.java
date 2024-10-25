package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.application.port.out.command.SubscribeCommand;

public interface ManageSubscriptionPort {
    void subscribe(SubscribeCommand command);
}
