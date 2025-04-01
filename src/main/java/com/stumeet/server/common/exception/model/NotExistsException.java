package com.stumeet.server.common.exception.model;

import com.stumeet.server.common.response.ErrorCode;

public class NotExistsException extends BusinessException {
    public NotExistsException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
