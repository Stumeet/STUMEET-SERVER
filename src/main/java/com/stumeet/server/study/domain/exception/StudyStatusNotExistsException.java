package com.stumeet.server.study.domain.exception;

import java.text.MessageFormat;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

public class StudyStatusNotExistsException extends NotExistsException {

	public static final String MESSAGE = "존재하지 않는 스터디 상태 입니다. 전달받은 상태 : {0}";

	public StudyStatusNotExistsException(String status) {
		super(MessageFormat.format(MESSAGE, status), ErrorCode.STUDY_STATUS_NOT_FOUND);
	}
}
