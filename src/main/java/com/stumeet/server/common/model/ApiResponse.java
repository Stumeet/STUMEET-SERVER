package com.stumeet.server.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stumeet.server.common.exception.error.Error;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.common.exception.error.ErrorField;
import com.stumeet.server.common.response.SuccessCode;

import java.util.List;
import org.springframework.validation.BindingResult;

public record ApiResponse<T>(
        int code,
        String message,
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        T data
) {

    public static <T> ApiResponse<T> success(SuccessCode code, T data) {
        return new ApiResponse<>(code.getHttpStatusCode(), code.getMessage(), data);
    }

    public static ApiResponse<Void> fail(ErrorCode errorCode) {
        return new ApiResponse<>(
                errorCode.getHttpStatusCode(),
                errorCode.getMessage(),
                null
        );
    }

    public static ApiResponse<List<Error>> fail(ErrorCode errorCode, BindingResult bindingResult) {
        return new ApiResponse<>(
                errorCode.getHttpStatusCode(),
                errorCode.getMessage(),
                ErrorField.toErrors(bindingResult)
        );
    }

    // TODO: 삭제 예정 메서드 : 해당 메서드를 사용 부분 수정 요망
    public static <T> ApiResponse<T> success(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    public static ApiResponse<Void> success(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public static ApiResponse<Void> fail(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}
