package com.stumeet.server.notification.adapter.out.persistence;

import java.util.List;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.notification.adapter.out.mapper.NotificationTokenPersistenceMapper;
import com.stumeet.server.notification.domain.exception.NotExistsNotificationTokenException;
import com.stumeet.server.notification.application.port.out.NotificationTokenQueryPort;
import com.stumeet.server.notification.application.port.out.SaveNotificationTokenPort;
import com.stumeet.server.notification.domain.NotificationToken;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class NotificationTokenPersistenceAdapter implements NotificationTokenQueryPort, SaveNotificationTokenPort {

    private final JpaNotificationTokenRepository jpaNotificationTokenRepository;

    private final NotificationTokenPersistenceMapper notificationTokenPersistenceMapper;

    @Override
    public NotificationToken findTokenForMember(Long memberId, String deviceId) {
        NotificationTokenJpaEntity entity = jpaNotificationTokenRepository.findByMemberIdAndDeviceId(memberId, deviceId)
                .orElseThrow(() -> new NotExistsNotificationTokenException(memberId));

        return notificationTokenPersistenceMapper.toDomain(entity);
    }

    @Override
    public List<NotificationToken> findTokensForMember(Long memberId) {
        List<NotificationTokenJpaEntity> entities = jpaNotificationTokenRepository.findAllByMemberId(memberId);
        return notificationTokenPersistenceMapper.toDomains(entities);
    }

    @Override
    public void save(NotificationToken notificationToken) {
        NotificationTokenJpaEntity entity = notificationTokenPersistenceMapper.toEntity(notificationToken);
        jpaNotificationTokenRepository.save(entity);
    }
}
