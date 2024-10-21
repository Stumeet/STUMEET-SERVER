package com.stumeet.server.notification.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.notification.adapter.out.persistence.entity.TopicJpaEntity;

public interface JpaTopicRepository extends JpaRepository<TopicJpaEntity, Long> {
}
