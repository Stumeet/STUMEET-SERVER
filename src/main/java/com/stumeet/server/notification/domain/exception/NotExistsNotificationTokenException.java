package com.stumeet.server.notification.domain.exception;

import java.text.MessageFormat;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

public class NotExistsNotificationTokenException extends NotExistsException {
    public static final String MESSAGE = "존재하지 않는 등록 토큰입니다. 입력받은 등록 토큰 ID : {0}";

    public NotExistsNotificationTokenException(Long notificationTokenId) {
        super(MessageFormat.format(MESSAGE, notificationTokenId), ErrorCode.NOTIFICATION_TOKEN_NOT_FOUND);
    }
}
