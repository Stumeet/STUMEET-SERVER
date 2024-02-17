package com.stumeet.server.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public record SuccessResponse<T>(
        int code,
        String message,
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        T data
) {

    public static <T> SuccessResponse<T> of(SuccessCode successCode, T data) {
        return new SuccessResponse<>(successCode.getHttpStatusCode(), successCode.getMessage(), data);
    }

    public static SuccessResponse<Void> of(SuccessCode successCode) {
        return new SuccessResponse<>(successCode.getHttpStatusCode(), successCode.getMessage(), null);
    }
}
