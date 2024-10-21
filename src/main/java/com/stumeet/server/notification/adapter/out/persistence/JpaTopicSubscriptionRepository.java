package com.stumeet.server.notification.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.notification.adapter.out.persistence.entity.TopicSubscriptionJpaEntity;

public interface JpaTopicSubscriptionRepository extends JpaRepository<TopicSubscriptionJpaEntity, Long> {
}
