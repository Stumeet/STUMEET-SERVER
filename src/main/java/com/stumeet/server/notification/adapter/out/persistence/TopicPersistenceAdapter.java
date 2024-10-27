package com.stumeet.server.notification.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.notification.adapter.out.mapper.TopicPersistenceMapper;
import com.stumeet.server.notification.adapter.out.persistence.entity.TopicJpaEntity;
import com.stumeet.server.notification.application.port.out.SaveTopicPort;
import com.stumeet.server.notification.application.port.out.TopicQueryPort;
import com.stumeet.server.notification.application.port.out.TopicValidationPort;
import com.stumeet.server.notification.domain.Topic;
import com.stumeet.server.notification.domain.TopicType;
import com.stumeet.server.notification.domain.exception.NotExistsStudyNoticeTopicException;
import com.stumeet.server.notification.domain.exception.NotExistsTopicException;
import com.stumeet.server.notification.domain.exception.TopicAlreadyExistsException;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TopicPersistenceAdapter implements SaveTopicPort, TopicQueryPort, TopicValidationPort {

    private final JpaTopicRepository jpaTopicRepository;

    private final TopicPersistenceMapper topicPersistenceMapper;

    @Override
    public Topic getById(Long id) {
        TopicJpaEntity entity = jpaTopicRepository.findById(id)
            .orElseThrow(() -> new NotExistsTopicException(id));

        return topicPersistenceMapper.toDomain(entity);
    }

    @Override
    public Topic getByTypeAndReferId(TopicType type, Long referId) {
        TopicJpaEntity entity = jpaTopicRepository.findByTypeAndReferId(type, referId)
            .orElseThrow(() -> new NotExistsStudyNoticeTopicException(referId));

        return topicPersistenceMapper.toDomain(entity);
    }

    @Override
    public void save(Topic domain) {
        TopicJpaEntity entity = topicPersistenceMapper.toEntity(domain);
        jpaTopicRepository.save(entity);
    }

    @Override
    public void validateUnique(TopicType type, Long referId) {
        if (jpaTopicRepository.existsByTypeAndReferId(type, referId)) {
            throw new TopicAlreadyExistsException(type.name(), referId);
        }
    }
}
