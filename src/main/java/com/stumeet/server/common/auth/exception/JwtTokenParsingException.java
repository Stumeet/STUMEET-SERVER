package com.stumeet.server.common.auth.exception;

import com.stumeet.server.common.response.ErrorCode;
import org.springframework.security.core.AuthenticationException;

public class JwtTokenParsingException extends AuthenticationException {
    private final ErrorCode errorCode;

    public JwtTokenParsingException(ErrorCode errorCode, Throwable e) {
        super(errorCode.getMessage(), e);
        this.errorCode = errorCode;
    }
}
