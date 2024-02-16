package com.stumeet.server.member.adapter.in.web;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.member.application.port.in.MemberSignupCommand;
import com.stumeet.server.member.application.port.in.MemberSignupUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberSignupApi {

    private final MemberSignupUseCase memberSignupUseCase;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(
            @AuthenticationPrincipal LoginMember loginMember,
            @RequestBody @Valid MemberSignupCommand request
    ) {
        memberSignupUseCase.signup(loginMember.getMember(), request);

        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.CREATED.value(), "회원가입에 성공했습니다."),
                HttpStatus.CREATED
        );
    }
}
