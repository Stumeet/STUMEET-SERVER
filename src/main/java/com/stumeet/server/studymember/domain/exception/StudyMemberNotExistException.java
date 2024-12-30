package com.stumeet.server.studymember.domain.exception;

import java.text.MessageFormat;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

public class StudyMemberNotExistException extends NotExistsException {

    public static final String MESSAGE = "존재하지 않는 스터디 멤버입니다. 전달받은 id : {0}";

    public StudyMemberNotExistException(Long id) {
        super(MessageFormat.format(MESSAGE, id), ErrorCode.STUDY_MEMBER_NOT_FOUND);
    }
}
