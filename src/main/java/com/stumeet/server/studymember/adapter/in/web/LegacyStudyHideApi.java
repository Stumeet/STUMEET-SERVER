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
import com.stumeet.server.studymember.application.port.in.LegacyStudyHideUseCase;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1/studies/{studyId}")
@RequiredArgsConstructor
public class LegacyStudyHideApi {

    private final LegacyStudyHideUseCase legacyStudyHideUseCase;

    @PatchMapping("/legacy/hide")
    public ResponseEntity<ApiResponse<Void>> hideLegacyStudy(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "studyId") Long studyId
    ) {
        legacyStudyHideUseCase.hideLegacyStudyForMember(studyId, member.getId());

        return ResponseEntity.ok(ApiResponse.success(SuccessCode.UPDATE_SUCCESS));
    }
}
