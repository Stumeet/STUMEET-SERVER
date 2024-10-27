package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.domain.Topic;
import com.stumeet.server.notification.domain.TopicType;

public interface TopicQueryPort {

    Topic getById(Long id);

    Topic getByTypeAndReferId(TopicType type, Long referId);
}
