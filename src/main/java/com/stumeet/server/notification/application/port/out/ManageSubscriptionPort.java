package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.application.port.out.command.SubscribeCommand;
import com.stumeet.server.notification.application.port.out.command.UnsubscribeCommand;

public interface ManageSubscriptionPort {
    void subscribe(SubscribeCommand command);

    void unsubscribe(UnsubscribeCommand command);
}
