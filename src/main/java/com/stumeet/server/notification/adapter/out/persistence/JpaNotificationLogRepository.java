package com.stumeet.server.notification.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stumeet.server.notification.adapter.out.persistence.entity.NotificationLogJpaEntity;
import com.stumeet.server.notification.adapter.out.web.dto.NotificationLogResponse;

public interface JpaNotificationLogRepository extends JpaRepository<NotificationLogJpaEntity, Long> {

    @Query("SELECT new com.stumeet.server.notification.adapter.out.web.dto.NotificationLogResponse("
        + "nl.id, nl.title, nl.body, nl.imgUrl, nl.data, nl.createdAt) "
        + "FROM NotificationLogJpaEntity nl "
        + "WHERE nl.memberId = :memberId "
        + "ORDER BY nl.createdAt DESC")
    Page<NotificationLogResponse> findMemberNotificationLogs(Long memberId, Pageable pageable);
}
