package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.domain.TopicType;

public interface TopicValidationPort {
    void validateUnique(TopicType type, Long referId);
}
