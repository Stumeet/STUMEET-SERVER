package com.stumeet.server.study.domain.exception;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

public class StudyFieldNotExistsException extends NotExistsException {
	public StudyFieldNotExistsException(Long id) {
		super("존재하지 않는 스터디 분야입니다. 전달받은 id : " + id, ErrorCode.STUDY_FIELD_NOT_FOUND);
	}
}
