package com.stumeet.server.common.auth.exception;

import com.stumeet.server.common.response.ErrorCode;
import org.springframework.security.core.AuthenticationException;


public class JwtInvalidSignatureException extends AuthenticationException {

    private final ErrorCode errorCode;

    public JwtInvalidSignatureException(ErrorCode errorCode, Throwable e) {
        super(errorCode.getMessage(), e);
        this.errorCode = errorCode;
    }

}
