package com.stumeet.server.notification.adapter.out.notification;

import org.springframework.stereotype.Component;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.TopicManagementResponse;
import com.stumeet.server.common.exception.model.NotificationException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.notification.application.port.out.ManageSubscriptionPort;
import com.stumeet.server.notification.application.port.out.NotificationSendPort;
import com.stumeet.server.notification.application.port.out.command.SendMessageCommand;
import com.stumeet.server.notification.application.port.out.command.SubscribeCommand;
import com.stumeet.server.notification.application.port.out.command.UnsubscribeCommand;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FcmNotificationAdapter implements ManageSubscriptionPort, NotificationSendPort {

    @Override
    public void subscribe(SubscribeCommand command) {
        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance()
                    .subscribeToTopic(command.registrationTokens(), command.topic());

            log.info("[subscription] success: " + response.getSuccessCount() + ", failure: " + response.getFailureCount());
        } catch (FirebaseMessagingException e) {
            String message = makeErrorMessage(e);
            throw new NotificationException(message, e, ErrorCode.EXTERNAL_SERVICE_UNAVAILABLE_ERROR);
        }
    }

    @Override
    public void unsubscribe(UnsubscribeCommand command) {
        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance()
                .unsubscribeFromTopic(command.registrationTokens(), command.topic());

            log.info("[unsubscription] success: " + response.getSuccessCount() + ", failure: " + response.getFailureCount());
        } catch (FirebaseMessagingException e) {
            String message = makeErrorMessage(e);
            throw new NotificationException(message, e, ErrorCode.EXTERNAL_SERVICE_UNAVAILABLE_ERROR);
        }
    }

    @Override
    public void sendTokenMulticastMessage(SendMessageCommand command) {
        try {
            Notification notification = makeNotification(command.title(), command.body(), command.image());

            MulticastMessage multicastMessage = MulticastMessage.builder()
                .addAllTokens(command.registrationTokens())
                .putAllData(command.data())
                .setNotification(notification)
                .build();

            BatchResponse response = FirebaseMessaging.getInstance()
                .sendEachForMulticast(multicastMessage);

            log.info("[notification] success: " + response.getSuccessCount() + ", failure: " + response.getFailureCount());
            log.info(response.toString());
        } catch (FirebaseMessagingException e) {
            String message = makeErrorMessage(e);
            throw new NotificationException(message, e, ErrorCode.EXTERNAL_SERVICE_UNAVAILABLE_ERROR);
        }
    }

    @Override
    public void sendTopicMessage(SendMessageCommand command) {
        try {
            Notification notification = makeNotification(command.title(), command.body(), command.image());

            Message message = Message.builder()
                .setTopic(command.topic())
                .putAllData(command.data())
                .setNotification(notification)
                .build();

            String response = FirebaseMessaging.getInstance().send(message);

            log.info("[notification] success\n" + response);
        } catch (FirebaseMessagingException e) {
            String message = makeErrorMessage(e);
            throw new NotificationException(message, e, ErrorCode.EXTERNAL_SERVICE_UNAVAILABLE_ERROR);
        }
    }

    private Notification makeNotification(String title, String body, String image) {
        return Notification.builder()
                .setTitle(title)
                .setBody(body)
                .setImage(image)
                .build();
    }

    private String makeErrorMessage(FirebaseMessagingException e) {
        return e.getErrorCode() + " : " + e.getMessage();
    }
}
