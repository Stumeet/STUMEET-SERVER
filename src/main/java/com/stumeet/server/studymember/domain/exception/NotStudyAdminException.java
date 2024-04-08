package com.stumeet.server.studymember.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

public class NotStudyAdminException extends InvalidStateException {
    public NotStudyAdminException(String message) {
        super(message, ErrorCode.NOT_STUDY_ADMIN_EXCEPTION);
    }

}
