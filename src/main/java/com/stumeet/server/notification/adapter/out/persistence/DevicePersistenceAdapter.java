package com.stumeet.server.notification.adapter.out.persistence;

import java.util.List;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.notification.adapter.out.mapper.NotificationTokenPersistenceMapper;
import com.stumeet.server.notification.adapter.out.persistence.entity.DeviceJpaEntity;
import com.stumeet.server.notification.domain.Device;
import com.stumeet.server.notification.domain.exception.NotExistsNotificationTokenException;
import com.stumeet.server.notification.application.port.out.DeviceQueryPort;
import com.stumeet.server.notification.application.port.out.SaveDevicePort;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class DevicePersistenceAdapter implements DeviceQueryPort, SaveDevicePort {

    private final JpaDeviceRepository jpaDeviceRepository;

    private final NotificationTokenPersistenceMapper notificationTokenPersistenceMapper;

    @Override
    public Device findTokenForMember(Long memberId, String deviceId) {
        DeviceJpaEntity entity = jpaDeviceRepository.findByMemberIdAndDeviceId(memberId, deviceId)
                .orElseThrow(() -> new NotExistsNotificationTokenException(memberId));

        return notificationTokenPersistenceMapper.toDomain(entity);
    }

    @Override
    public List<Device> findTokensForMember(Long memberId) {
        List<DeviceJpaEntity> entities = jpaDeviceRepository.findAllByMemberId(memberId);
        return notificationTokenPersistenceMapper.toDomains(entities);
    }

    @Override
    public void save(Device device) {
        DeviceJpaEntity entity = notificationTokenPersistenceMapper.toEntity(device);
        jpaDeviceRepository.save(entity);
    }
}
