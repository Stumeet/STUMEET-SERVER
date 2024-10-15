package com.stumeet.server.notification.application.port.out;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.stumeet.server.notification.application.port.out.command.SendMessageCommand;

public interface NotificationSendPort {
    void sendTokenMulticastMessage(SendMessageCommand command) throws FirebaseMessagingException;
    void sendTopicMessage(SendMessageCommand command) throws FirebaseMessagingException;
}
