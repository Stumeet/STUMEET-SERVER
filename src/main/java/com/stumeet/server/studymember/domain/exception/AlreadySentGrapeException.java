package com.stumeet.server.studymember.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

public class AlreadySentGrapeException extends InvalidStateException {
    public AlreadySentGrapeException() {
        super(ErrorCode.ALREADY_SENT_GRAPE_ERROR);
    }
}
