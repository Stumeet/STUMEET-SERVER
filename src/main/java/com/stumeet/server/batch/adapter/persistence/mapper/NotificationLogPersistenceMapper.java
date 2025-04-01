package com.stumeet.server.batch.adapter.persistence.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stumeet.server.batch.domain.member.NotificationLog;
import com.stumeet.server.batch.dto.Notification;

@Component
public class NotificationLogPersistenceMapper {

    public NotificationLog toDomain(Notification notification) {
            return NotificationLog.builder()
                .title(notification.title())
                .body(notification.body())
                .imgUrl(notification.imgUrl())
                .data(notification.data())
                .memberId(notification.memberId())
                .build();
    }

    public List<NotificationLog> toDomains(List<Notification> notifications) {
        return notifications.stream()
            .map(this::toDomain)
            .toList();
    }
}
