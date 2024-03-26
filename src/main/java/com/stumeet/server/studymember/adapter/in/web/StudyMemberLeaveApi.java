package com.stumeet.server.studymember.adapter.in.web;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.studymember.application.port.in.StudyMemberLeaveUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudyMemberLeaveApi {

    private final StudyMemberLeaveUseCase studyMemberLeaveUseCase;

    @DeleteMapping("/studies/{studyId}/members/me")
    public ResponseEntity<ApiResponse<Void>> leave(
            @PathVariable Long studyId,
            @AuthenticationPrincipal LoginMember member
    ) {
        studyMemberLeaveUseCase.leave(studyId, member.getMember().getId());
        return new ResponseEntity<>(ApiResponse.success(SuccessCode.STUDY_LEAVE_SUCCESS), HttpStatus.OK);
    }
}
