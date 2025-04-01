package com.stumeet.server.notification.domain.exception;

import java.text.MessageFormat;

import com.stumeet.server.common.exception.model.AlreadyExistsException;
import com.stumeet.server.common.response.ErrorCode;

public class TopicAlreadyExistsException extends AlreadyExistsException {

    public static final String MESSAGE = "이미 존재하는 토픽입니다. 토픽 유형 : {0}, 참조 ID : {1}";

    public TopicAlreadyExistsException(String topicType, Long referId) {
        super(MessageFormat.format(MESSAGE, topicType, referId), ErrorCode.ALREADY_TOPIC_EXISTS);
    }
}
