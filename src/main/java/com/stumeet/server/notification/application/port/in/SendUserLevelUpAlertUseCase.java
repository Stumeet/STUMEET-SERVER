package com.stumeet.server.notification.application.port.in;

public interface SendUserLevelUpAlertUseCase {

    void sendUserLevelUpAlert(long memberId, String tierName);
}
