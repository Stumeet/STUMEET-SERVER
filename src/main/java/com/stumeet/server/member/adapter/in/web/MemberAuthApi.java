package com.stumeet.server.member.adapter.in.web;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.member.adapter.in.web.response.TokenResponse;
import com.stumeet.server.member.application.port.in.MemberProfileUseCase;
import com.stumeet.server.member.application.port.in.MemberTokenUseCase;
import com.stumeet.server.member.application.port.in.command.MemberSignupCommand;
import com.stumeet.server.member.application.port.in.command.TokenRenewCommand;

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
public class MemberAuthApi {

    private final MemberTokenUseCase memberTokenUseCase;
    private final MemberProfileUseCase memberProfileUseCase;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(
            @AuthenticationPrincipal LoginMember loginMember,
            @Valid MemberSignupCommand request
    ) {
        memberProfileUseCase.signup(loginMember.getMember(), request);

        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.OK.value(), "회원가입에 성공했습니다."),
                HttpStatus.OK
        );
    }

    @PostMapping("/tokens")
    public ResponseEntity<ApiResponse<TokenResponse>> renewAccessToken(
            @RequestBody @Valid TokenRenewCommand request
    ) {
        TokenResponse response = memberTokenUseCase.renewTokens(request);

        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.OK.value(), "토큰 재발급에 성공했습니다.", response),
                HttpStatus.OK
        );
    }
}
