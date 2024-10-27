package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.domain.TopicSubscription;

public interface SaveTopicSubscriptionPort {

    void save(TopicSubscription topicSubscription);
}
