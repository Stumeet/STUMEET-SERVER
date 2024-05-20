package com.stumeet.server.study.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.study.adapter.in.web.response.JoinedStudiesResponse;
import com.stumeet.server.study.adapter.in.web.response.StudyDetailResponse;
import com.stumeet.server.study.application.port.in.StudyQueryUseCase;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1/studies")
@RequiredArgsConstructor
public class StudyQueryApi {

	private final StudyQueryUseCase studyQueryUseCase;

	@GetMapping("/{studyId}")
	public ResponseEntity<ApiResponse<StudyDetailResponse>> getStudyDetail(
		@AuthenticationPrincipal LoginMember member,
		@PathVariable(name = "studyId") Long studyId
	) {
		StudyDetailResponse response = studyQueryUseCase.getStudyDetailById(studyId);
		return ResponseEntity.ok(ApiResponse.success(SuccessCode.GET_SUCCESS, response));
	}

	@GetMapping("/joined")
	public ResponseEntity<ApiResponse<JoinedStudiesResponse>> getStudiesInvolved(
		@AuthenticationPrincipal LoginMember member
	) {
		JoinedStudiesResponse response = studyQueryUseCase.getJoinedStudies(member.getMember().getId());
		return ResponseEntity.ok(ApiResponse.success(SuccessCode.GET_SUCCESS, response));
	}
}
