package com.stumeet.server.notification.adapter.out.persistence;

import java.util.List;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.notification.adapter.out.mapper.TopicSubscriptionPersistenceMapper;
import com.stumeet.server.notification.adapter.out.persistence.entity.TopicSubscriptionJpaEntity;
import com.stumeet.server.notification.application.port.out.DeleteTopicSubscriptionPort;
import com.stumeet.server.notification.application.port.out.SaveTopicSubscriptionPort;
import com.stumeet.server.notification.application.port.out.TopicSubscriptionQueryPort;
import com.stumeet.server.notification.domain.TopicSubscription;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TopicSubscriptionPersistenceAdapter
    implements TopicSubscriptionQueryPort, SaveTopicSubscriptionPort, DeleteTopicSubscriptionPort {

    private final JpaTopicSubscriptionRepository jpaTopicSubscriptionRepository;
    private final TopicSubscriptionPersistenceMapper topicSubscriptionPersistenceMapper;

    @Override
    public List<TopicSubscription> getAllForMember(Long memberId) {
        return topicSubscriptionPersistenceMapper.toDomains(
            jpaTopicSubscriptionRepository.findAllByMemberId(memberId)
        );
    }

    @Override
    public boolean isExists(Long memberId, Long topicId) {
        return jpaTopicSubscriptionRepository.existsByMemberIdAndTopicId(memberId, topicId);
    }

    @Override
    public void save(TopicSubscription topicSubscription) {
        TopicSubscriptionJpaEntity entity = topicSubscriptionPersistenceMapper.toEntity(topicSubscription);
        jpaTopicSubscriptionRepository.save(entity);
    }

    @Override
    public void delete(Long memberId, Long topicId) {
        jpaTopicSubscriptionRepository.deleteByMemberIdAndTopicId(memberId, topicId);
    }
}
