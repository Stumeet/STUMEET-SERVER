package com.stumeet.server.notification.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.notification.adapter.out.persistence.entity.TopicJpaEntity;
import com.stumeet.server.notification.domain.TopicType;

public interface JpaTopicRepository extends JpaRepository<TopicJpaEntity, Long> {

    Optional<TopicJpaEntity> findByTypeAndReferId(TopicType type, Long referId);
}
