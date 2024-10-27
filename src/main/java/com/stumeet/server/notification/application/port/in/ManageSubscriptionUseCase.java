package com.stumeet.server.notification.application.port.in;

import com.stumeet.server.notification.domain.Device;

public interface ManageSubscriptionUseCase {

    void subscribeStudyNoticeTopic(Long memberId, Long studyId);

    void unsubscribeStudyNoticeTopic(Long memberId, Long studyId);

    void renewSubscription(Device device);
}
