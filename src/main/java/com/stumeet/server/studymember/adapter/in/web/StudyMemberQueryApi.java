package com.stumeet.server.studymember.adapter.in.web;

import java.util.List;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.studymember.application.port.in.response.MyStudyMemberDetailResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberDetailResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberResponses;
import com.stumeet.server.studymember.application.port.in.StudyMemberQueryUseCase;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberReviewStatusResponse;

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

    @GetMapping("/studies/{studyId}/members/{memberId}")
    public ResponseEntity<ApiResponse<StudyMemberDetailResponse>> getStudyMemberDetail(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable Long studyId,
            @PathVariable Long memberId
    ) {
        StudyMemberDetailResponse response = studyMemberQueryUseCase.getStudyMemberDetail(studyId, memberId, member.getId());

        return ResponseEntity.ok(
                ApiResponse.success(SuccessCode.GET_SUCCESS, response)
        );
    }

    @GetMapping("/studies/{studyId}/members/me")
    public ResponseEntity<ApiResponse<MyStudyMemberDetailResponse>> getMyStudyMemberDetail(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable Long studyId
    ) {
        MyStudyMemberDetailResponse response = studyMemberQueryUseCase.getMyStudyMemberDetail(studyId, member.getId());

        return ResponseEntity.ok(
                ApiResponse.success(SuccessCode.GET_SUCCESS, response)
        );
    }

    @GetMapping("/studies/{studyId}/members/review-status")
    public ResponseEntity<ApiResponse<List<StudyMemberReviewStatusResponse>>> getStudyMembersReviewStatus(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable Long studyId
    ) {
        List<StudyMemberReviewStatusResponse> response =
                studyMemberQueryUseCase.getStudyMemberReviewStatusByMember(studyId, member.getId());

        return ResponseEntity.ok(
                ApiResponse.success(SuccessCode.GET_SUCCESS, response)
        );
    }
}
