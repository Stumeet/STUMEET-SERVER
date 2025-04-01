package com.stumeet.server.member.adapter.in.web;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberValidationApi {

    private final MemberValidationUseCase memberValidationUseCase;

    @GetMapping("/validate-nickname")
    public ResponseEntity<ApiResponse<Void>> isDuplicateNickname(
            @RequestParam
            @Size(min = 2, max = 10, message = "닉네임은 2 ~ 10자 사이로 입력해주세요")
            String nickname
    ) {
        memberValidationUseCase.validateNickname(nickname);

        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.OK.value(), "유효한 닉네임입니다."),
                HttpStatus.OK
        );
    }

}
