package com.stumeet.server.notification.adapter.out.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.notification.adapter.out.persistence.entity.TopicJpaEntity;
import com.stumeet.server.notification.adapter.out.persistence.entity.TopicSubscriptionJpaEntity;
import com.stumeet.server.notification.domain.TopicSubscription;

@Component
public class TopicSubscriptionPersistenceMapper {

    public TopicSubscriptionJpaEntity toEntity(TopicSubscription domain) {
        return TopicSubscriptionJpaEntity.builder()
            .id(domain.getId())
            .topic(TopicJpaEntity.builder()
                .id(domain.getTopic().getId())
                .build())
            .memberId(domain.getMemberId())
            .build();
    }
}
