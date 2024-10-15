package com.stumeet.server.notification.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class NotificationToken {
    private final Long id;
    private final Long memberId;
    private final String deviceId;
    private final String token;

    public NotificationToken renewNotificationToken(String token) {
        return NotificationToken.builder()
                .id(this.id)
                .memberId(this.memberId)
                .deviceId(this.deviceId)
                .token(token)
                .build();
    }
}