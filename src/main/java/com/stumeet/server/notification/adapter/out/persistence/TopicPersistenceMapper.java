package com.stumeet.server.notification.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.stumeet.server.notification.adapter.out.persistence.entity.TopicJpaEntity;
import com.stumeet.server.notification.domain.Topic;

@Component
public class TopicPersistenceMapper {

    public TopicJpaEntity toEntity(Topic domain) {
        return TopicJpaEntity.builder()
            .id(domain.getId())
            .name(domain.getName())
            .description(domain.getDescription())
            .type(domain.getType())
            .referId(domain.getReferId())
            .build();
    }

    public Topic toDomain(TopicJpaEntity entity) {
        return Topic.builder()
            .id(entity.getId())
            .name(entity.getName())
            .description(entity.getDescription())
            .type(entity.getType())
            .referId(entity.getReferId())
            .build();
    }
}
