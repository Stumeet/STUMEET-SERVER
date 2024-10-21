package com.stumeet.server.notification.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.notification.adapter.out.persistence.entity.TopicJpaEntity;
import com.stumeet.server.notification.application.port.out.SaveTopicPort;
import com.stumeet.server.notification.application.port.out.TopicQueryPort;
import com.stumeet.server.notification.domain.Topic;
import com.stumeet.server.notification.domain.exception.NotExistsTopicException;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TopicPersistenceAdapter implements SaveTopicPort, TopicQueryPort {

    private final JpaTopicRepository jpaTopicRepository;

    private final TopicPersistenceMapper topicPersistenceMapper;

    @Override
    public Topic findById(Long id) {
        TopicJpaEntity entity = jpaTopicRepository.findById(id)
            .orElseThrow(() -> new NotExistsTopicException(id));

        return topicPersistenceMapper.toDomain(entity);
    }

    @Override
    public Long save(Topic domain) {
        TopicJpaEntity entity = topicPersistenceMapper.toEntity(domain);
        return jpaTopicRepository.save(entity).getId();
    }
}
