package com.stumeet.server.activity.application.exception;

import com.stumeet.server.common.exception.model.BadRequestException;
import com.stumeet.server.common.response.ErrorCode;

public class InvalidPaginationParametersException extends BadRequestException {
	public InvalidPaginationParametersException() {
		super(ErrorCode.INVALID_PAGINATION_PARAMETERS_EXCEPTION);
	}
}
