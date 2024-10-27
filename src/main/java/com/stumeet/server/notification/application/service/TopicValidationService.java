package com.stumeet.server.notification.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.notification.application.port.in.TopicValidationUseCase;
import com.stumeet.server.notification.application.port.out.TopicValidationPort;
import com.stumeet.server.notification.domain.TopicType;
import com.stumeet.server.notification.domain.exception.TopicAlreadyExistsException;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TopicValidationService implements TopicValidationUseCase {

    private final TopicValidationPort topicValidationPort;

    @Override
    public void validateUnique(TopicType type, Long referId) {
        if (topicValidationPort.existsByTypeAndId(type, referId)) {
            throw new TopicAlreadyExistsException(type.name(), referId);
        }
    }
}
