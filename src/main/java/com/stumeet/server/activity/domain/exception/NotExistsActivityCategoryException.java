package com.stumeet.server.activity.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

import java.text.MessageFormat;

public class NotExistsActivityCategoryException extends InvalidStateException {
    private static final String MESSAGE = "존재하지 않는 활동 카테고리입니다. 입력받은 카테고리 : {0}";

    public NotExistsActivityCategoryException(String category) {
        super(MessageFormat.format(MESSAGE, category), ErrorCode.INVALID_ACTIVITY_CATEGORY_EXCEPTION);
    }
}
