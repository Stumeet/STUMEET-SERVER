package com.stumeet.server.common.exception.model;

import com.stumeet.server.common.response.ErrorCode;

public class BadRequestException extends BusinessException{
	public BadRequestException(ErrorCode errorCode) {
		super(errorCode);
	}

	public BadRequestException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}
}
