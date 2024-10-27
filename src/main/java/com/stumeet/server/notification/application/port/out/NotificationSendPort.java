package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.application.port.out.command.SendMessageCommand;

public interface NotificationSendPort {
    void sendTokenMulticastMessage(SendMessageCommand command);
    void sendTopicMessage(SendMessageCommand command);
}
