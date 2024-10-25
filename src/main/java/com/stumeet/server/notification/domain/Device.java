package com.stumeet.server.notification.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Device {
    private final Long id;
    private final Long memberId;
    private final String deviceId;
    private final String notificationToken;

    public Device renewNotificationToken(String notificationToken) {
        return Device.builder()
                .id(this.id)
                .memberId(this.memberId)
                .deviceId(this.deviceId)
                .notificationToken(notificationToken)
                .build();
    }
}