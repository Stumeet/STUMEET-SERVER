package com.stumeet.server.notification.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class TopicSubscription {

    private Long id;
    @NonNull private Topic topic;
    private Long memberId;

    @Builder
    private TopicSubscription(Long id, Topic topic, Long memberId) {
        this.id = id;
        this.topic = topic;
        this.memberId = memberId;
    }

    public static TopicSubscription create(Topic topic, Long memberId) {
        return TopicSubscription.builder()
            .topic(topic)
            .memberId(memberId)
            .build();
    }
}
