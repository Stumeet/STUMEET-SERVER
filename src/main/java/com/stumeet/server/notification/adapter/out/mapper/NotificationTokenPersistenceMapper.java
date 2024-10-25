package com.stumeet.server.notification.adapter.out.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stumeet.server.notification.adapter.out.persistence.entity.DeviceJpaEntity;
import com.stumeet.server.notification.domain.Device;

@Component
public class NotificationTokenPersistenceMapper {

    public Device toDomain(DeviceJpaEntity entity) {
        return Device.builder()
            .id(entity.getId())
            .memberId(entity.getMemberId())
            .deviceId(entity.getDeviceId())
            .notificationToken(entity.getToken())
            .build();
    }

    public DeviceJpaEntity toEntity(Device domain) {
        return DeviceJpaEntity.builder()
            .id(domain.getId())
            .memberId(domain.getMemberId())
            .deviceId(domain.getDeviceId())
            .token(domain.getNotificationToken())
            .build();
    }

    public List<Device> toDomains(List<DeviceJpaEntity> entities) {
        return entities.stream().map(this::toDomain)
            .toList();
    }
}
