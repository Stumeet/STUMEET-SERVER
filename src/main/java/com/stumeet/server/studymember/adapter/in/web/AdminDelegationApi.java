package com.stumeet.server.studymember.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.studymember.application.port.in.AdminDelegationUseCase;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdminDelegationApi {

    private final AdminDelegationUseCase adminDelegationUseCase;

    @PatchMapping("/studies/{studyId}/members/{memberId}/admin/delegate")
    public ResponseEntity<ApiResponse<Void>> delegateAdmin(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable Long studyId,
            @PathVariable Long memberId
    ) {
        adminDelegationUseCase.delegateAdmin(studyId, member.getId(), memberId);

        return ResponseEntity.ok(ApiResponse.success(SuccessCode.UPDATE_SUCCESS));
    }
}
