package com.stumeet.server.notification.application.port.out.command;

import java.util.List;
import java.util.Map;

import lombok.Builder;

@Builder
public record SendMessageCommand(
        List<String> registrationTokens,
        String topic,
        String title,
        String body,
        String image,
        Map<String, String> data
) {
}
