package com.stumeet.server.common.auth.exception;

import com.stumeet.server.common.response.ErrorCode;
import org.springframework.security.core.AuthenticationException;

public class IllegalKeyAlgorithmException extends AuthenticationException {
    private final ErrorCode errorCode;

    public IllegalKeyAlgorithmException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public IllegalKeyAlgorithmException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}
