package com.stumeet.server.notification.domain.exception;

import java.text.MessageFormat;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

public class NotExistsTopicException extends NotExistsException {

    public static final String MESSAGE = "존재하지 않는 토픽입니다. 입력받은 토픽 ID : {0}";

    public NotExistsTopicException(Long topicId) {
        super(MessageFormat.format(MESSAGE, topicId), ErrorCode.TOPIC_NOT_FOUND);
    }
}
