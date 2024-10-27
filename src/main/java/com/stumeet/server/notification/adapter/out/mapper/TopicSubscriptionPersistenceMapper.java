package com.stumeet.server.notification.adapter.out.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stumeet.server.notification.adapter.out.persistence.entity.TopicJpaEntity;
import com.stumeet.server.notification.adapter.out.persistence.entity.TopicSubscriptionJpaEntity;
import com.stumeet.server.notification.domain.Topic;
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

    public TopicSubscription toDomain(TopicSubscriptionJpaEntity entity) {
        return TopicSubscription.builder()
            .id(entity.getId())
            .topic(Topic.builder()
                .id(entity.getTopic().getId())
                .build())
            .memberId(entity.getMemberId())
            .build();
    }

    public List<TopicSubscription> toDomains(List<TopicSubscriptionJpaEntity> entities) {
        return entities.stream()
            .map(this::toDomain)
            .toList();
    }
}
