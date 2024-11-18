package com.stumeet.server.batch.dto;

import java.util.List;
import java.util.Map;

public record Notification(
    Long memberId,
    String title,
    String body,
    String imgUrl,
    Map<String, String> data,
    String topic,
    List<String> tokens
) {
}
