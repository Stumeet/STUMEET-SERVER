package com.stumeet.server.studymember.adapter.in.web;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberResponses;
import com.stumeet.server.studymember.application.port.in.StudyMemberQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudyMemberQueryApi {

    private final StudyMemberQueryUseCase studyMemberQueryUseCase;

    @GetMapping("/studies/{studyId}/members")
    public ResponseEntity<ApiResponse<StudyMemberResponses>> getStudyMembers(
            @PathVariable Long studyId,
            @AuthenticationPrincipal LoginMember member
    ) {
        StudyMemberResponses responses = studyMemberQueryUseCase.getStudyMembers(studyId, member.getMember().getId());
        return new ResponseEntity<>(
                ApiResponse.success(SuccessCode.GET_SUCCESS, responses),
                HttpStatus.OK
        );
    }
}
