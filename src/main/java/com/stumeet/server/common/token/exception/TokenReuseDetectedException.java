package com.stumeet.server.common.token.exception;

import com.stumeet.server.common.exception.model.SecurityViolationException;
import com.stumeet.server.common.response.ErrorCode;

import lombok.Getter;

@Getter
public class TokenReuseDetectedException extends SecurityViolationException {

    public TokenReuseDetectedException(Long memberId) {
        super(
                String.format("Refresh Token 재사용이 감지되었습니다. memberId: %d", memberId),
                ErrorCode.REFRESH_TOKEN_REUSED_EXCEPTION);
    }
}
