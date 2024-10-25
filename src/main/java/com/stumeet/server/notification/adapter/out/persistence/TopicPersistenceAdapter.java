package com.stumeet.server.notification.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.notification.adapter.out.mapper.TopicPersistenceMapper;
import com.stumeet.server.notification.adapter.out.persistence.entity.TopicJpaEntity;
import com.stumeet.server.notification.application.port.out.SaveTopicPort;
import com.stumeet.server.notification.application.port.out.TopicQueryPort;
import com.stumeet.server.notification.domain.Topic;
import com.stumeet.server.notification.domain.TopicType;
import com.stumeet.server.notification.domain.exception.NotExistsStudyNoticeTopicException;
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
    public Topic findStudyNoticeTopic(Long studyId) {
        TopicJpaEntity entity = jpaTopicRepository.findByTypeAndReferId(TopicType.STUDY_NOTICE, studyId)
            .orElseThrow(() -> new NotExistsStudyNoticeTopicException(studyId));

        return topicPersistenceMapper.toDomain(entity);
    }

    @Override
    public void save(Topic domain) {
        TopicJpaEntity entity = topicPersistenceMapper.toEntity(domain);
        jpaTopicRepository.save(entity);
    }
}
