package com.stumeet.server.notification.application.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.model.PageInfoResponse;
import com.stumeet.server.notification.adapter.out.web.dto.NotificationLogResponse;
import com.stumeet.server.notification.adapter.out.web.dto.NotificationLogResponses;
import com.stumeet.server.notification.application.port.in.NotificationLogQueryUseCase;
import com.stumeet.server.notification.application.port.out.NotificationLogQueryPort;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class NotificationLogQueryService implements NotificationLogQueryUseCase {

    private final NotificationLogQueryPort notificationLogQueryPort;

    @Override
    public NotificationLogResponses getMemberNotificationLogs(Long memberId, Integer size, Integer page) {
        Page<NotificationLogResponse> notificationLogs =
            notificationLogQueryPort.getMemberNotificationLogs(memberId, size, page);
        PageInfoResponse pageInfo = PageInfoResponse.builder()
            .currentPage(page)
            .pageSize(size)
            .totalPages(notificationLogs.getTotalPages())
            .totalElements(notificationLogs.getTotalElements())
            .build();

        return new NotificationLogResponses(notificationLogs.toList(), pageInfo);
    }
}
