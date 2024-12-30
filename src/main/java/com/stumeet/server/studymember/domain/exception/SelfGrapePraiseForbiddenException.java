package com.stumeet.server.studymember.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

public class SelfGrapePraiseForbiddenException extends InvalidStateException {

    public SelfGrapePraiseForbiddenException() {
        super(ErrorCode.SELF_GRAPE_PRAISE_FORBIDDEN_EXCEPTION);
    }
}
