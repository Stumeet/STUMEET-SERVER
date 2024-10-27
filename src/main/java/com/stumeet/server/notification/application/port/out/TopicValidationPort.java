package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.domain.TopicType;

public interface TopicValidationPort {
    boolean existsByTypeAndId(TopicType type, Long referId);
}
