package com.stumeet.server.studymember.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

public class StudyMemberNotJoinedException extends InvalidStateException {

    public StudyMemberNotJoinedException(String message) {
        super(message, ErrorCode.STUDY_MEMBER_NOT_JOINED_EXCEPTION);
    }
}
