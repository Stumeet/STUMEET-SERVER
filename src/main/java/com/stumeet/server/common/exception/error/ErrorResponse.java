package com.stumeet.server.common.exception.error;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private final int code;
    private String message;
    private final List<Error> errors;

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.getHttpStatusCode(),
                List.of(new Error(errorCode.getMessage()))
        );
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        return new ErrorResponse(
                errorCode.getHttpStatusCode(),
                errorCode.getMessage(),
                ErrorField.of(bindingResult));
    }
}
