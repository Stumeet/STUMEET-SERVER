package com.stumeet.server.common.exception.model;

import com.stumeet.server.common.response.ErrorCode;

public class FormatConversionException extends BusinessException {
    private static final String MESSAGE = "데이터 형식 변환 중 에러가 발생했습니다.";

    public FormatConversionException(Throwable cause, ErrorCode errorCode) {
        super(MESSAGE, cause, errorCode);
    }
}
