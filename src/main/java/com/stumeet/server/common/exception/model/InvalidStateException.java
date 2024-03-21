package com.stumeet.server.common.exception.model;

import com.stumeet.server.common.response.ErrorCode;

public class InvalidStateException extends BusinessException {
    public InvalidStateException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidStateException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
