package com.stumeet.server.notification.application.port.out;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stumeet.server.batch.dto.Notification;

public interface NotificationQueuePort {

    void publishNotifications(List<Notification> notifications) throws JsonProcessingException;
}
