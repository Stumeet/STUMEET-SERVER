package com.stumeet.server.notification.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.notification.adapter.out.persistence.entity.TopicJpaEntity;
import com.stumeet.server.notification.application.port.out.SaveTopicPort;
import com.stumeet.server.notification.domain.Topic;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TopicPersistenceAdapter implements SaveTopicPort {

    private final JpaTopicRepository jpaTopicRepository;

    private final TopicPersistenceMapper topicPersistenceMapper;

    @Override
    public void save(Topic domain) {
        TopicJpaEntity entity = topicPersistenceMapper.toEntity(domain);
        jpaTopicRepository.save(entity);
    }
}
