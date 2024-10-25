package com.stumeet.server.notification.application.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.notification.application.port.in.SubscribeTopicUseCase;
import com.stumeet.server.notification.application.port.out.ManageSubscriptionPort;
import com.stumeet.server.notification.application.port.out.NotificationTokenQueryPort;
import com.stumeet.server.notification.application.port.out.SaveTopicSubscriptionPort;
import com.stumeet.server.notification.application.port.out.TopicQueryPort;
import com.stumeet.server.notification.application.port.out.command.SubscribeCommand;
import com.stumeet.server.notification.domain.NotificationToken;
import com.stumeet.server.notification.domain.Topic;
import com.stumeet.server.notification.domain.TopicSubscription;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class SubscribeTopicService implements SubscribeTopicUseCase {

    private final TopicQueryPort topicQueryPort;
    private final NotificationTokenQueryPort notificationTokenQueryPort;

    private final SaveTopicSubscriptionPort saveTopicSubscriptionPort;
    private final ManageSubscriptionPort manageSubscriptionPort;

    @Override
    public void subscribeStudyNoticeTopic(Long memberId, Long studyId) {
        Topic topic = topicQueryPort.findStudyNoticeTopic(studyId);
        TopicSubscription topicSubscription = TopicSubscription.create(topic, memberId);
        saveTopicSubscriptionPort.save(topicSubscription);

        List<String> tokens = notificationTokenQueryPort.findTokensForMember(memberId)
            .stream().map(NotificationToken::getToken)
            .toList();

        SubscribeCommand subscribeCommand = new SubscribeCommand(tokens, topic.getName());
        manageSubscriptionPort.subscribe(subscribeCommand);
    }
}
