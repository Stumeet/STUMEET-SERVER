package com.stumeet.server.notification.adapter.out.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stumeet.server.batch.dto.Notification;
import com.stumeet.server.notification.application.port.out.NotificationQueuePort;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
@RequiredArgsConstructor
public class SQSNotificationAdapter implements NotificationQueuePort {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    @Value("${spring.cloud.config.server.aws.sqs.url}")
    private String url;

    @Override
    public void publishNotifications(List<Notification> notifications) throws JsonProcessingException {
        SendMessageRequest request = SendMessageRequest.builder()
            .queueUrl(url)
            .messageBody(objectMapper.writeValueAsString(notifications))
            .build();

        sqsClient.sendMessage(request);
    }
}
