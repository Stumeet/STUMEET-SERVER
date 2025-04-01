package com.stumeet.server.activity.domain.exception;

import java.text.MessageFormat;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

public class NotExistsActivitySortException  extends InvalidStateException {
    private static final String MESSAGE = "존재하지 않는 활동 정렬 입니다. 입력받은 정렬 : {0}";

    public NotExistsActivitySortException(String sort) {
        super(MessageFormat.format(MESSAGE, sort), ErrorCode.INVALID_ACTIVITY_SORT_EXCEPTION);
    }
}
