package com.stumeet.server.activity.domain.exception;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

import java.text.MessageFormat;

public class NotExistsActivityException extends NotExistsException {
    private static final String MESSAGE = "존재하지 않는 활동입니다. 입력받은 활동 ID : {0}";


    public NotExistsActivityException(Long activityId) {
        super(MessageFormat.format(MESSAGE, activityId), ErrorCode.ACTIVITY_NOT_FOUND);
    }
}
