package com.stumeet.server.notification.application.port.out;

public interface DeleteTopicSubscriptionPort {

    void delete(Long memberId, Long topicId);
}
