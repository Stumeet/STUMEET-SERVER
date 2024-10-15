package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.domain.NotificationToken;

public interface NotificationTokenQueryPort {
    NotificationToken findTokenForMember(Long memberId, String deviceId);
}
