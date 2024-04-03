package com.stumeet.server.study.domain.exception;

import com.stumeet.server.common.exception.model.BadRequestException;
import com.stumeet.server.common.response.ErrorCode;

public class InvalidRepetitionDatesException extends BadRequestException {
	public InvalidRepetitionDatesException(String repetitionType) {
		super("유효하지 않은 반복일 값 입니다. 반복유형: " + repetitionType, ErrorCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION);
	}
}
