package com.stumeet.server.notification.application.service;

import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.notification.application.port.in.InitializeTopicUseCase;
import com.stumeet.server.notification.application.port.in.TopicValidationUseCase;
import com.stumeet.server.notification.application.port.out.SaveTopicPort;
import com.stumeet.server.notification.domain.Topic;
import com.stumeet.server.notification.domain.TopicType;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class InitializeTopicService implements InitializeTopicUseCase {
    private static final String STUDY_NOTICE_DESCRIPTION_TEMPLATE = "ID: %d 스터디 공지 토픽";
    private static final String STUDY_NOTICE_TOPIC_TEMPLATE = "%d_STUDY_NOTICE_%s";

    private final StudyValidationUseCase studyValidationUseCase;
    private final TopicValidationUseCase topicValidationUseCase;

    private final SaveTopicPort saveTopicPort;

    @Override
    public void initializeStudyNoticeTopic(Long studyId) {
        studyValidationUseCase.checkById(studyId);
        topicValidationUseCase.validateUnique(TopicType.STUDY_NOTICE, studyId);

        String topicName = String.format(STUDY_NOTICE_TOPIC_TEMPLATE, studyId, UUID.randomUUID());

        Topic topic = Topic.builder()
            .name(topicName)
            .description(String.format(STUDY_NOTICE_DESCRIPTION_TEMPLATE, studyId))
            .type(TopicType.STUDY_NOTICE)
            .referId(studyId)
            .build();

        saveTopicPort.save(topic);
    }
}
