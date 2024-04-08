package com.stumeet.server.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /**
     * 200 - OK
     */
    GET_SUCCESS(HttpStatus.OK, "조회에 성공했습니다."),
    STUDY_LEAVE_SUCCESS(HttpStatus.OK, "스터디 탈퇴에 성공했습니다."),

    /**
     * 201 - CREATED
     */
    POST_SUCCESS(HttpStatus.CREATED, "생성에 성공했습니다."),
    SIGN_UP_SUCCESS(HttpStatus.CREATED, "회원가입에 성공했습니다."),
    FILE_UPLOAD_SUCCESS(HttpStatus.CREATED, "파일 업로드에 성공했습니다."),
    STUDY_CREATE_SUCCESS(HttpStatus.CREATED, "스터디 그룹 생성에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
