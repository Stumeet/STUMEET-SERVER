package com.stumeet.server.common.exception.model;

import com.stumeet.server.common.response.ErrorCode;

public class EnumValidationException extends BusinessException {
    private static final String MESSAGE_TEMPLATE = "enum %s 검증 실패, 입력값: %s";

    public <E extends Enum<E>> EnumValidationException(ErrorCode errorCode, Class<E> enumClass, String invalidValue) {
        super(String.format(MESSAGE_TEMPLATE, enumClass.getSimpleName(), invalidValue), errorCode);
    }
}
