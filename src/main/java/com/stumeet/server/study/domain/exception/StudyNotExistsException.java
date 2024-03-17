package com.stumeet.server.study.domain.exception;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

public class StudyNotExistsException extends NotExistsException {
    public StudyNotExistsException(Long id, ErrorCode errorCode) {
        super("존재하지 않는 스터디입니다. 전달받은 id : " + id, errorCode);
    }
}
