package com.stumeet.server.studymember.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

public class AlreadyStudyJoinMemberException extends InvalidStateException {
    public AlreadyStudyJoinMemberException(String message) {
        super(message, ErrorCode.ALREADY_STUDY_JOIN_MEMBER_EXCEPTION);
    }
}
