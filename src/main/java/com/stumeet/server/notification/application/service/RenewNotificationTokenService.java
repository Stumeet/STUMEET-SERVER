package com.stumeet.server.notification.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.notification.application.port.in.RenewNotificationTokenUseCase;
import com.stumeet.server.notification.application.port.in.command.RenewNotificationTokenCommand;
import com.stumeet.server.notification.application.port.out.NotificationTokenQueryPort;
import com.stumeet.server.notification.application.port.out.SaveNotificationTokenPort;
import com.stumeet.server.notification.domain.NotificationToken;
import com.stumeet.server.notification.domain.exception.NotExistsNotificationTokenException;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RenewNotificationTokenService implements RenewNotificationTokenUseCase {

    private final NotificationTokenQueryPort notificationTokenQueryPort;
    private final SaveNotificationTokenPort saveNotificationTokenPort;

    @Override
    public void renewNotificationToken(RenewNotificationTokenCommand command) {
        NotificationToken token;
        try {
            token = notificationTokenQueryPort
                    .findTokenForMember(command.memberId(), command.deviceId())
                    .renewNotificationToken(command.notificationToken());
        } catch (NotExistsNotificationTokenException exception) {
            token = NotificationToken.builder()
                    .memberId(command.memberId())
                    .deviceId(command.deviceId())
                    .token(command.notificationToken())
                    .build();
        }

        saveNotificationTokenPort.save(token);
    }
}
