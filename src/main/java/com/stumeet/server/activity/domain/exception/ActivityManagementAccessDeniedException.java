package com.stumeet.server.activity.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

public class ActivityManagementAccessDeniedException extends InvalidStateException {
    public ActivityManagementAccessDeniedException() {
        super(ErrorCode.ACTIVITY_MANAGEMENT_ACCESS_DENIED_EXCEPTION);
    }
}
