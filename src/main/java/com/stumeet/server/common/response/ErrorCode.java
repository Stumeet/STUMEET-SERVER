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
    NOT_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, "요청으로 전달한 값이 존재하지 않습니다."),
    FILE_SIZE_LIMIT_EXCEEDED_EXCEPTION(HttpStatus.BAD_REQUEST, "첨부파일은 최대 5MB 까지 가능합니다."),
    INVALID_PAGINATION_PARAMETERS_EXCEPTION(HttpStatus.BAD_REQUEST, "제공된 페이지네이션 매개변수가 유효하지 않습니다. 'page'와 'size' 매개변수를 함께 포함하거나 함께 생략해야 합니다."),
    INVALID_PERIOD_EXCEPTION(HttpStatus.BAD_REQUEST, "종료일이 시작일보다 빠릅니다."),

    DUPLICATE_NICKNAME_EXCEPTION(HttpStatus.BAD_REQUEST, "닉네임이 중복되었습니다."),
    NOT_MATCHED_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "요청으로 전달한 토큰과 매칭되는 토큰이 없습니다."),
    NOT_MATCHED_REFRESH_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "요청으로 전달한 리프레시 토큰과 서버의 리프레시 토큰이 일치하지 않습니다."),
    EXPIRED_REFRESH_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "리프레시 토큰이 만료되었습니다."),

    INVALID_FILE_NAME_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 파일 이름입니다."),
    INVALID_FILE_CONTENT_TYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 파일 컨텐트 타입 입니다."),
    INVALID_FILE_EXTENSION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 파일 확장자입니다."),
  
    INVALID_ACTIVITY_CATEGORY_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 활동 카테고리입니다."),
    START_DATE_NOT_YET_EXCEPTION(HttpStatus.BAD_REQUEST, "시작일 전에 스터디를 완료할 수 없습니다."),


    /*
        401 - UNAUTHORIZED
    */
    JWT_INVALID_SIGNATURE_EXCEPTION(HttpStatus.UNAUTHORIZED, "JWT 서명이 유효하지 않습니다."),
    ILLEGAL_KEY_ALGORITHM_EXCEPTION(HttpStatus.UNAUTHORIZED, "유효하지 않은 키 알고리즘입니다."),
    JWT_TOKEN_PARSING_EXCEPTION(HttpStatus.UNAUTHORIZED, "JWT 토큰 파싱에 실패했습니다."),
    NOT_EXIST_OAUTH_PROVIDER(HttpStatus.UNAUTHORIZED, "존재하지 않는 OAuth 제공자입니다."),


    /*
      403 - FORBIDDEN
    */
    ACCESS_DENIED_EXCEPTION(HttpStatus.FORBIDDEN, "유효하지 않은 요청입니다."),
    STUDY_MEMBER_NOT_JOINED_EXCEPTION(HttpStatus.FORBIDDEN, "스터디에 가입한 멤버가 아닙니다."),
    NOT_STUDY_ADMIN_EXCEPTION(HttpStatus.FORBIDDEN, "스터디 관리자가 아닙니다."),
    ALREADY_STUDY_JOIN_MEMBER_EXCEPTION(HttpStatus.FORBIDDEN, "스터디에 이미 가입한 사용자입니다."),
    NOT_EXIST_ACTIVITY_STATUS_EXCEPTION(HttpStatus.FORBIDDEN, "존재하지 않는 활동 상태입니다."),
    ACTIVITY_MANAGEMENT_ACCESS_DENIED_EXCEPTION(HttpStatus.FORBIDDEN, "활동 관리 권한이 없습니다."),

    /*
        404 - NOT FOUND
    */
    STUDY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 스터디 입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 멤버 입니다."),
    STUDY_FIELD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 스터디 분야 입니다."),
    STUDY_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 스터디 상태 입니다."),
    ACTIVITY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 활동 입니다."),

    /*
        500 - INTERNAL SERVER ERROR
    */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다."),
    FILE_IO_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 입출력에 실패하였습니다."),
    UPLOAD_FILE_FAIL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패하였습니다."),
    NOT_IMPLEMENTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "구현되지 않은 메서드를 사용했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
