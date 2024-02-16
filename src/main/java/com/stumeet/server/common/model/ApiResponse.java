package com.stumeet.server.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public record ApiResponse<T>(
        int code,
        String message,
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        T data
) {
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
