package com.stumeet.server.common.exception.model;

import com.stumeet.server.common.response.ErrorCode;

public class SecurityViolationException extends BusinessException {
    public SecurityViolationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
