package com.stumeet.server.common.exception.model;

import com.stumeet.server.common.response.ErrorCode;

public class NotificationException extends BusinessException{

    public NotificationException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }
}
