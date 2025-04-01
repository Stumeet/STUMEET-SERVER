package com.stumeet.server.notification.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.notification.adapter.out.web.dto.NotificationLogResponse;
import com.stumeet.server.notification.application.port.out.NotificationLogQueryPort;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class NotificationLogPersistenceAdapter implements NotificationLogQueryPort {
    private final JpaNotificationLogRepository jpaNotificationLogRepository;

    @Override
    public Page<NotificationLogResponse> getMemberNotificationLogs(Long memberId, Integer size, Integer page) {
        return jpaNotificationLogRepository.findMemberNotificationLogs(memberId, PageRequest.of(page, size));
    }
}