package com.stumeet.server.activity.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

public class InvalidCategoryStatusException extends InvalidStateException {
    public InvalidCategoryStatusException() {
        super(ErrorCode.INVALID_STATUS_FOR_ACTIVITY_CATEGORY);
    }
}
