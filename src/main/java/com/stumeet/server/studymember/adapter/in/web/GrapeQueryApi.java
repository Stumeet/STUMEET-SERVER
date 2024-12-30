package com.stumeet.server.studymember.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.studymember.adapter.in.web.dto.GrapeResponses;
import com.stumeet.server.studymember.application.port.in.MemberGrapeQueryUseCase;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Validated
public class GrapeQueryApi {
    private final MemberGrapeQueryUseCase memberGrapeQueryUseCase;

    @GetMapping("/grapes")
    public ResponseEntity<ApiResponse<GrapeResponses>> getMemberGrapes(
        @AuthenticationPrincipal LoginMember member,
        @NotNull @RequestParam(defaultValue = "5") int size,
        @NotNull @RequestParam(defaultValue = "0") int page
    ) {
        GrapeResponses response = memberGrapeQueryUseCase.findMemberGrapes(member.getId(), page, size);

        return new ResponseEntity<>(
            ApiResponse.success(SuccessCode.GET_SUCCESS, response),
            HttpStatus.OK
        );
    }
}
