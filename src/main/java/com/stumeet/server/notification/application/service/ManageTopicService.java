package com.stumeet.server.notification.application.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.notification.application.port.in.ManageSubscriptionUseCase;
import com.stumeet.server.notification.application.port.out.DeleteTopicSubscriptionPort;
import com.stumeet.server.notification.application.port.out.ManageSubscriptionPort;
import com.stumeet.server.notification.application.port.out.DeviceQueryPort;
import com.stumeet.server.notification.application.port.out.SaveTopicSubscriptionPort;
import com.stumeet.server.notification.application.port.out.TopicQueryPort;
import com.stumeet.server.notification.application.port.out.command.SubscribeCommand;
import com.stumeet.server.notification.application.port.out.command.UnsubscribeCommand;
import com.stumeet.server.notification.domain.Device;
import com.stumeet.server.notification.domain.Topic;
import com.stumeet.server.notification.domain.TopicSubscription;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class ManageTopicService implements ManageSubscriptionUseCase {

    private final TopicQueryPort topicQueryPort;
    private final DeviceQueryPort deviceQueryPort;

    private final SaveTopicSubscriptionPort saveTopicSubscriptionPort;
    private final ManageSubscriptionPort manageSubscriptionPort;
    private final DeleteTopicSubscriptionPort deleteTopicSubscriptionPort;

    @Override
    public void subscribeStudyNoticeTopic(Long memberId, Long studyId) {
        Topic topic = topicQueryPort.findStudyNoticeTopic(studyId);
        TopicSubscription topicSubscription = TopicSubscription.create(topic, memberId);
        saveTopicSubscriptionPort.save(topicSubscription);

        List<String> tokens = deviceQueryPort.findTokensForMember(memberId)
            .stream().map(Device::getNotificationToken)
            .toList();

        SubscribeCommand subscribeCommand = new SubscribeCommand(tokens, topic.getName());
        manageSubscriptionPort.subscribe(subscribeCommand);
    }

    @Override
    public void unsubscribeStudyNoticeTopic(Long memberId, Long studyId) {
        Topic topic = topicQueryPort.findStudyNoticeTopic(studyId);
        deleteTopicSubscriptionPort.delete(memberId, topic.getId());

        List<String> tokens = deviceQueryPort.findTokensForMember(memberId)
            .stream().map(Device::getNotificationToken)
            .toList();

        UnsubscribeCommand unsubscribeCommand = new UnsubscribeCommand(tokens, topic.getName());
        manageSubscriptionPort.unsubscribe(unsubscribeCommand);
    }
}
