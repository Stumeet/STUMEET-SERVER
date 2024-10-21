package com.stumeet.server.notification.application.port.out;

import java.util.List;

import com.stumeet.server.notification.domain.NotificationToken;

public interface NotificationTokenQueryPort {
    NotificationToken findTokenForMember(Long memberId, String deviceId);

    List<NotificationToken> findTokensForMember(Long memberId);
}
