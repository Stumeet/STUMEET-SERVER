package com.stumeet.server.notification.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationTokenRepository extends JpaRepository<NotificationTokenJpaEntity, Long> {

    Optional<NotificationTokenJpaEntity> findByMemberIdAndDeviceId(Long memberId, String deviceId);
}
