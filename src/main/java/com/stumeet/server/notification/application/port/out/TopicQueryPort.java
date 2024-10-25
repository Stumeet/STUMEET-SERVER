package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.domain.Topic;

public interface TopicQueryPort {

    Topic findById(Long id);

    Topic findStudyNoticeTopic(Long studyId);
}
