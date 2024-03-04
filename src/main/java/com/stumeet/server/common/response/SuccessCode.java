package com.stumeet.server.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    SIGN_UP_SUCCESS(HttpStatus.CREATED, "회원가입에 성공했습니다."),
    FILE_UPLOAD_SUCCESS(HttpStatus.CREATED, "파일 업로드에 성공했습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
