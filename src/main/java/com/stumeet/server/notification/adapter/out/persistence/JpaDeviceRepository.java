package com.stumeet.server.notification.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.notification.adapter.out.persistence.entity.DeviceJpaEntity;

public interface JpaDeviceRepository extends JpaRepository<DeviceJpaEntity, Long> {

    Optional<DeviceJpaEntity> findByMemberIdAndDeviceId(Long memberId, String deviceId);

    List<DeviceJpaEntity> findAllByMemberId(Long memberId);
}
