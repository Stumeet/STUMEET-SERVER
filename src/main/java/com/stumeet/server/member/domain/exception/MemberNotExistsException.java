package com.stumeet.server.member.domain.exception;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

public class MemberNotExistsException extends NotExistsException {
    public MemberNotExistsException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
