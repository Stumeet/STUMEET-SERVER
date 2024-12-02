package com.stumeet.server.batch.adapter.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.batch.domain.member.NotificationLog;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {
}
