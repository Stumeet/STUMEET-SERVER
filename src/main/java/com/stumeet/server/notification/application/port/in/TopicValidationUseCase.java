package com.stumeet.server.notification.application.port.in;

import com.stumeet.server.notification.domain.TopicType;

public interface TopicValidationUseCase {

    void validateUnique(TopicType type, Long referId);
}
