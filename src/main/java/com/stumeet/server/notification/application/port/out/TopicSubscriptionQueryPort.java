package com.stumeet.server.notification.application.port.out;

import java.util.List;

import com.stumeet.server.notification.domain.TopicSubscription;

public interface TopicSubscriptionQueryPort {
    List<TopicSubscription> getAllForMember(Long memberId);

    boolean isExists(Long memberId, Long topicId);
}
