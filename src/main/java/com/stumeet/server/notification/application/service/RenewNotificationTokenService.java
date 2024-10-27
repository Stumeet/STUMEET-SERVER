package com.stumeet.server.notification.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.notification.application.port.in.ManageSubscriptionUseCase;
import com.stumeet.server.notification.application.port.in.RenewNotificationTokenUseCase;
import com.stumeet.server.notification.application.port.in.command.RenewNotificationTokenCommand;
import com.stumeet.server.notification.application.port.out.DeviceQueryPort;
import com.stumeet.server.notification.application.port.out.SaveDevicePort;
import com.stumeet.server.notification.domain.Device;
import com.stumeet.server.notification.domain.exception.NotExistsNotificationTokenException;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RenewNotificationTokenService implements RenewNotificationTokenUseCase {

    private final ManageSubscriptionUseCase manageSubscriptionUseCase;

    private final DeviceQueryPort deviceQueryPort;
    private final SaveDevicePort saveDevicePort;

    @Override
    public void renewNotificationToken(RenewNotificationTokenCommand command) {
        Device device;
        try {
            device = deviceQueryPort
                    .findDeviceForMember(command.memberId(), command.deviceId())
                    .renewNotificationToken(command.notificationToken());
        } catch (NotExistsNotificationTokenException exception) {
            device = Device.builder()
                    .memberId(command.memberId())
                    .deviceId(command.deviceId())
                    .notificationToken(command.notificationToken())
                    .build();
        }
        saveDevicePort.save(device);

        manageSubscriptionUseCase.renewSubscription(device);
    }
}
