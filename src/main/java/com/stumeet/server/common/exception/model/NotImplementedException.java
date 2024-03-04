package com.stumeet.server.common.exception.model;

import com.stumeet.server.common.response.ErrorCode;

public class NotImplementedException extends BusinessException{
	public NotImplementedException() {
		super(ErrorCode.NOT_IMPLEMENTED_ERROR);
	}
}
