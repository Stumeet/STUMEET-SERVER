package com.stumeet.server.file.domain.exception;

import com.stumeet.server.common.exception.model.InvalidResourceException;
import com.stumeet.server.common.response.ErrorCode;

public class InvalidFileException extends InvalidResourceException {
	public InvalidFileException(ErrorCode errorCode) {
		super(errorCode);
	}
}
