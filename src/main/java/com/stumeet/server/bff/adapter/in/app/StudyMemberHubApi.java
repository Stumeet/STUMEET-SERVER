package com.stumeet.server.bff.adapter.in.app;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stumeet.server.bff.adapter.in.app.response.StudyMemberDetailFullResponse;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.studymember.application.port.in.StudyMemberQueryUseCase;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberDetailResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/external/v1")
@RequiredArgsConstructor
public class StudyMemberHubApi {

    private final StudyMemberQueryUseCase studyMemberQueryUseCase;

    @GetMapping("/studies/{studyId}/members/{memberId}")
    public ResponseEntity<ApiResponse<StudyMemberDetailFullResponse>> getStudyMemberDetailFull(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable Long studyId,
            @PathVariable Long memberId
    ) {
        StudyMemberDetailResponse studyMemberDetail = studyMemberQueryUseCase.getStudyMemberDetail(studyId, memberId, member.getId());
        boolean isAdmin = studyMemberQueryUseCase.isMemberAdmin(studyId, memberId).isAdmin();
        boolean canSendGrape = studyMemberQueryUseCase.canStudyMemberSendGrape(studyId, memberId).canSendGrape();

        StudyMemberDetailFullResponse response = new StudyMemberDetailFullResponse(
                studyMemberDetail,
                isAdmin,
                canSendGrape
        );

        return ResponseEntity.ok(
                ApiResponse.success(SuccessCode.GET_SUCCESS, response)
        );
    }
}
