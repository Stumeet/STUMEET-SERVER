package com.stumeet.server.notification.application.port.in.command;

public record RenewNotificationTokenCommand(
        Long memberId,
        String deviceId,
        String notificationToken
) {
}
