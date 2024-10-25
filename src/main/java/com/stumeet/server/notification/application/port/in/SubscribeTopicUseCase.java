package com.stumeet.server.notification.application.port.in;

public interface SubscribeTopicUseCase {

    void subscribeStudyNoticeTopic(Long memberId, Long studyId);
}
