package com.stumeet.server.notification.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.notification.adapter.out.mapper.TopicSubscriptionPersistenceMapper;
import com.stumeet.server.notification.adapter.out.persistence.entity.TopicSubscriptionJpaEntity;
import com.stumeet.server.notification.application.port.out.SaveTopicSubscriptionPort;
import com.stumeet.server.notification.domain.TopicSubscription;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TopicSubscriptionPersistenceAdapter implements SaveTopicSubscriptionPort {

    private final JpaTopicSubscriptionRepository jpaTopicSubscriptionRepository;
    private final TopicSubscriptionPersistenceMapper topicSubscriptionPersistenceMapper;

    @Override
    public void save(TopicSubscription topicSubscription) {
        TopicSubscriptionJpaEntity entity = topicSubscriptionPersistenceMapper.toEntity(topicSubscription);
        jpaTopicSubscriptionRepository.save(entity);
    }
}
