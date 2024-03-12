package com.stumeet.server.common.exception.model;

import com.stumeet.server.common.response.ErrorCode;

public class InvalidFileException extends BusinessException {
	public InvalidFileException(ErrorCode errorCode) {
		super(errorCode);
	}
}
