package com.stumeet.server.notification.application.port.out;

public interface TopicSubscriptionQueryPort {
    boolean isExists(Long memberId, Long topicId);
}
