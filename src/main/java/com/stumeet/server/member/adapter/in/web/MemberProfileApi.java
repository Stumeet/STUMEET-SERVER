package com.stumeet.server.member.adapter.in.web;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.member.adapter.in.web.response.MemberProfileResponse;
import com.stumeet.server.member.application.port.in.MemberProfileUseCase;
import com.stumeet.server.member.application.port.in.command.MemberUpdateCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberProfileApi {

    private final MemberProfileUseCase memberProfileUseCase;

    @PatchMapping("/me")
    public ResponseEntity<ApiResponse<Void>> updateMyProfile(
            @AuthenticationPrincipal LoginMember member,
            @Valid MemberUpdateCommand request
    ) {
        memberProfileUseCase.updateProfile(member.getMember(), request);
        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.OK.value(), "내 프로필 수정에 성공했습니다."),
                HttpStatus.OK
        );
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MemberProfileResponse>> getMyProfile(
            @AuthenticationPrincipal LoginMember member
    ) {
        MemberProfileResponse response = memberProfileUseCase.getProfileById(member.getMember().getId());

        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.OK.value(), "내 프로필 조회에 성공했습니다.", response),
                HttpStatus.OK
        );
    }
}
