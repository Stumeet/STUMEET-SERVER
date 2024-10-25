package com.stumeet.server.notification.application.port.in;

public interface ManageSubscriptionUseCase {

    void subscribeStudyNoticeTopic(Long memberId, Long studyId);
}
