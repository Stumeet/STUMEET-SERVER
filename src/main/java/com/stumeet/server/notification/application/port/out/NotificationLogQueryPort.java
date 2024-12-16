package com.stumeet.server.notification.application.port.out;

import org.springframework.data.domain.Page;

import com.stumeet.server.notification.adapter.out.web.dto.NotificationLogResponse;

public interface NotificationLogQueryPort {
    Page<NotificationLogResponse> getMemberNotificationLogs(Long memberId, Integer size, Integer page);
}
