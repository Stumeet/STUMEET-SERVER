package com.stumeet.server.notification.adapter.out.web.dto;

import java.util.List;

import com.stumeet.server.common.model.PageInfoResponse;

public record NotificationLogResponses(
    List<NotificationLogResponse> notificationLogs,
    PageInfoResponse pageInfo
) {
}
