package com.stumeet.server.notification.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.notification.adapter.out.persistence.entity.TopicSubscriptionJpaEntity;

public interface JpaTopicSubscriptionRepository extends JpaRepository<TopicSubscriptionJpaEntity, Long> {

    @EntityGraph(attributePaths = {"topic"})
    List<TopicSubscriptionJpaEntity> findAllByMemberId(Long memberId);

    boolean existsByMemberIdAndTopicId(Long memberId, Long topicId);

    void deleteByMemberIdAndTopicId(Long memberId, Long topicId);
}