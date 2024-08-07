package com.stumeet.server.activity.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

import java.text.MessageFormat;

public class NotExistsActivityStatusException extends InvalidStateException {
    private static final String MESSAGE = "존재하지 않는 상태입니다. 입력받은 상태 : {0}";
    public NotExistsActivityStatusException(String status) {
        super(MessageFormat.format(MESSAGE, status), ErrorCode.ACTIVITY_STATUS_NOT_FOUND);
    }
}
