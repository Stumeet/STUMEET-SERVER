package com.stumeet.server.notification.application.port.in;

public interface SendGrapePraiseAlertUseCase {

    void sendGrapeAlert(Long studyId, Long memberId);
}