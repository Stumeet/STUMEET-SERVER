package com.stumeet.server.common.exception.model;

import com.stumeet.server.common.response.ErrorCode;

public class AlreadyExistsException extends BusinessException{
    public AlreadyExistsException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
