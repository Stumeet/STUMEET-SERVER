package com.stumeet.server.common.exception.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    /*
        400 - BAD REQUEST
    */
    VALIDATION_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    BIND_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 값을 바인딩하는 과정에서 오류가 발생하였습니다."),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 값이 검증되지 않은 값 입니다."),
    METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 값의 타입이 잘못되었습니다."),
    INVALID_FORMAT_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 값이 유효하지 않은 데이터입니다."),

    /*
        500 - INTERNAL SERVER ERROR
    */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
