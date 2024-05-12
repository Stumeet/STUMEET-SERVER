package com.stumeet.server.studymember.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

import java.text.MessageFormat;

public class NotStudyAdminException extends InvalidStateException {

    public static final String MESSAGE = "스터디 관리자가 아닙니다. 전달받은 studyId={0}, memberId={1}";
    public NotStudyAdminException(Long studyId, Long memberId) {
        super(MessageFormat.format(MESSAGE, studyId, memberId), ErrorCode.NOT_STUDY_ADMIN_EXCEPTION);
    }

}
