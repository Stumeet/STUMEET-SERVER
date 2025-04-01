package com.stumeet.server.notification.application.port.out.command;

import java.util.List;

public record UnsubscribeCommand(
    List<String> registrationTokens,
    String topic
) {
}
