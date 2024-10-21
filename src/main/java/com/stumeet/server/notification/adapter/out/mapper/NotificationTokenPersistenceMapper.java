package com.stumeet.server.notification.adapter.out.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stumeet.server.notification.adapter.out.persistence.NotificationTokenJpaEntity;
import com.stumeet.server.notification.domain.NotificationToken;

@Component
public class NotificationTokenPersistenceMapper {

    public NotificationToken toDomain(NotificationTokenJpaEntity entity) {
        return NotificationToken.builder()
            .id(entity.getId())
            .memberId(entity.getMemberId())
            .deviceId(entity.getDeviceId())
            .token(entity.getToken())
            .build();
    }

    public NotificationTokenJpaEntity toEntity(NotificationToken domain) {
        return NotificationTokenJpaEntity.builder()
            .id(domain.getId())
            .memberId(domain.getMemberId())
            .deviceId(domain.getDeviceId())
            .token(domain.getToken())
            .build();
    }

    public List<NotificationToken> toDomains(List<NotificationTokenJpaEntity> entities) {
        return entities.stream().map(this::toDomain)
            .toList();
    }
}
