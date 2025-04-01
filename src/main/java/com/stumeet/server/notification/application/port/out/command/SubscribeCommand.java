package com.stumeet.server.notification.application.port.out.command;

import java.util.List;

public record SubscribeCommand(
        List<String> registrationTokens,
        String topic
) {
}
