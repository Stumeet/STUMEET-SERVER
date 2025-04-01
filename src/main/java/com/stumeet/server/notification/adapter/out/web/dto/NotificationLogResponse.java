package com.stumeet.server.notification.adapter.out.web.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record NotificationLogResponse(
    Long id,
    String title,
    String body,
    String imgUrl,
    Map<String, String> data,
    LocalDateTime createdAt
) {
}
