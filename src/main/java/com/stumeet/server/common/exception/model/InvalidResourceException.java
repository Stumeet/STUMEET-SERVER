package com.stumeet.server.common.exception.model;

import com.stumeet.server.common.response.ErrorCode;

public class InvalidResourceException extends BusinessException {
	public InvalidResourceException(ErrorCode errorCode) {
		super(errorCode);
	}
}
