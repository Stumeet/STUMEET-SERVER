package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.domain.NotificationToken;

public interface SaveNotificationTokenPort {
    void save(NotificationToken notificationToken);
}
