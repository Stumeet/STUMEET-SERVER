package com.stumeet.server.notification.application.service;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.notification.application.port.in.StudyNoticeSendUseCase;
import com.stumeet.server.notification.application.port.out.NotificationSendPort;
import com.stumeet.server.notification.application.port.out.TopicQueryPort;
import com.stumeet.server.notification.application.port.out.command.SendMessageCommand;
import com.stumeet.server.notification.domain.Topic;
import com.stumeet.server.notification.domain.TopicType;
import com.stumeet.server.study.application.port.in.StudyQueryUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class NotificationSendService implements StudyNoticeSendUseCase {
    private static final String STUDY_NOTICE_BODY_TEMPLATE = "\"%s\" 에 새로운 공지가 올라왔어요!";
    private static final String STUDY_ID = "studyId";
    private static final String ACTIVITY_ID = "activityId";

    private final StudyQueryUseCase studyQueryUseCase;

    private final TopicQueryPort topicQueryPort;
    private final NotificationSendPort notificationSendPort;

    @Override
    public void sendStudyNotice(Long studyId, Long activityId) {
        String studyName = studyQueryUseCase.getStudyName(studyId);
        Topic topic = topicQueryPort.getByTypeAndReferId(TopicType.STUDY_NOTICE, studyId);

        Map<String, String> data = Map.of(
            STUDY_ID, Long.toString(studyId),
            ACTIVITY_ID, Long.toString(activityId)
        );

        SendMessageCommand command = SendMessageCommand.builder()
            .topic(topic.getName())
            .title(studyName)
            .body(String.format(STUDY_NOTICE_BODY_TEMPLATE, studyName))
            .data(data)
            .image(null)
            .build();

        notificationSendPort.sendTopicMessage(command);
    }
}
