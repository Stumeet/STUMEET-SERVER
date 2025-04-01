package com.stumeet.server.notification.application.port.in;

import com.stumeet.server.notification.adapter.out.web.dto.NotificationLogResponses;

public interface NotificationLogQueryUseCase {

    NotificationLogResponses getMemberNotificationLogs(Long memberId, Integer size, Integer page);
}
