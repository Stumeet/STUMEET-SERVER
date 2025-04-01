package com.stumeet.server.study.domain.exception;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

import java.text.MessageFormat;

public class StudyNotExistsException extends NotExistsException {
    public static final String MESSAGE = "존재하지 않는 스터디입니다. 전달받은 id : {0}";
    public StudyNotExistsException(Long id) {
        super(MessageFormat.format(MESSAGE, id), ErrorCode.STUDY_NOT_FOUND);
    }
}
