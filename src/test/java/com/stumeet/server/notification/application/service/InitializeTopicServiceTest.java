package com.stumeet.server.notification.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.stumeet.server.notification.application.port.in.TopicValidationUseCase;
import com.stumeet.server.notification.application.port.out.SaveTopicPort;
import com.stumeet.server.notification.domain.Topic;
import com.stumeet.server.notification.domain.TopicType;
import com.stumeet.server.notification.domain.exception.TopicAlreadyExistsException;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;
import com.stumeet.server.template.UnitTest;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class InitializeTopicServiceTest extends UnitTest {
    static final Long STUDY_ID = StudyStub.getStudyIdWithoutTopic();

    @InjectMocks
    private InitializeTopicService initializeTopicService;
    @Mock
    private SaveTopicPort saveTopicPort;
    @Mock
    private StudyValidationUseCase studyValidationUseCase;
    @Mock
    private TopicValidationUseCase topicValidationUseCase;

    @Nested
    @DisplayName("알림 토픽 초기화")
    class InitializeTopic {

        @Test
        @DisplayName("[성공] 알림 토픽 초기화에 성공한다.")
        void success() {
            // given
            willDoNothing().given(studyValidationUseCase).checkById(STUDY_ID);
            willDoNothing().given(topicValidationUseCase).validateUnique(TopicType.STUDY_NOTICE, STUDY_ID);

            ArgumentCaptor<Topic> topicCaptor = ArgumentCaptor.forClass(Topic.class);

            // when
            initializeTopicService.initializeStudyNoticeTopic(STUDY_ID);

            // then
            then(studyValidationUseCase).should().checkById(STUDY_ID);
            then(topicValidationUseCase).should().validateUnique(TopicType.STUDY_NOTICE, STUDY_ID);
            then(saveTopicPort).should().save(topicCaptor.capture());

            Topic capturedTopic = topicCaptor.getValue();
            assertThat(capturedTopic.getName()).startsWith(STUDY_ID + "_STUDY_NOTICE");
            assertThat(capturedTopic.getType()).isEqualTo(TopicType.STUDY_NOTICE);
            assertThat(capturedTopic.getReferId()).isEqualTo(STUDY_ID);
        }

        @Test
        @DisplayName("[실패] 대상 스터디가 존재하지 않으면 알림 토픽 초기화에 성공한다.")
        void fail_when_requested_study_not_exist() {
            // given
            willThrow(new StudyNotExistsException(STUDY_ID))
                .given(studyValidationUseCase).checkById(STUDY_ID);

            // when & then
            assertThatThrownBy(() -> initializeTopicService.initializeStudyNoticeTopic(STUDY_ID))
                .isInstanceOf(StudyNotExistsException.class);

            then(studyValidationUseCase).should().checkById(STUDY_ID);
            then(topicValidationUseCase).shouldHaveNoInteractions();
            then(saveTopicPort).shouldHaveNoInteractions();
        }

        @Test
        @DisplayName("[실패] 알림 토픽이 이미 존재하면 알림 토픽 초기화에 성공한다.")
        void fail_when_topic_already_exists() {
            // given
            willThrow(new TopicAlreadyExistsException(TopicType.STUDY_NOTICE.name(), STUDY_ID))
                .given(topicValidationUseCase).validateUnique(TopicType.STUDY_NOTICE, STUDY_ID);

            // when & then
            assertThatThrownBy(() -> initializeTopicService.initializeStudyNoticeTopic(STUDY_ID))
                .isInstanceOf(TopicAlreadyExistsException.class);

            then(studyValidationUseCase).should().checkById(STUDY_ID);
            then(topicValidationUseCase).should().validateUnique(TopicType.STUDY_NOTICE, STUDY_ID);
            then(saveTopicPort).shouldHaveNoInteractions();
        }
    }
}