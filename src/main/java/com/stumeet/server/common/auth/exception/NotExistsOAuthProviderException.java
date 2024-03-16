package com.stumeet.server.common.auth.exception;

import com.stumeet.server.common.response.ErrorCode;
import org.springframework.security.core.AuthenticationException;

public class NotExistsOAuthProviderException extends AuthenticationException {

    public NotExistsOAuthProviderException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
