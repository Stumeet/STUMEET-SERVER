package com.stumeet.server.common.response;

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
    INVALID_FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 파일입니다."),
    INVALID_IMAGE_FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 이미지 파일입니다."),
    INVALID_FILE_EXTENSION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 파일 확장자입니다."),
    DUPLICATE_NICKNAME_EXCEPTION(HttpStatus.BAD_REQUEST, "닉네임이 중복되었습니다."),
    NOT_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, "요청으로 전달한 값이 존재하지 않습니다."),
    NOT_MATCHED_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "요청으로 전달한 토큰과 매칭되는 토큰이 없습니다."),
    NOT_MATCHED_REFRESH_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "요청으로 전달한 리프레시 토큰과 서버의 리프레시 토큰이 일치하지 않습니다."),
    EXPIRED_REFRESH_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "리프레시 토큰이 만료되었습니다."),

    /*
        403 - FORBIDDEN
    */
    ACCESS_DENIED_EXCEPTION(HttpStatus.FORBIDDEN, "유효하지 않은 요청입니다."),

    /*
        500 - INTERNAL SERVER ERROR
    */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다."),
    UPLOAD_FILE_FAIL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패하였습니다."),
    NOT_IMPLEMENTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "구현되지 않은 메서드를 사용했습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
