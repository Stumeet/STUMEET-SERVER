package com.stumeet.server.study.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.study.application.port.in.StudyFinishUseCase;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1/studies")
@RequiredArgsConstructor
public class StudyFinishApi {

	private final StudyFinishUseCase studyFinishUseCase;

	@PatchMapping("/{studyId}/finish")
	public ResponseEntity<ApiResponse<Void>> finish(
		@AuthenticationPrincipal LoginMember member,
		@PathVariable Long studyId
	) {
		studyFinishUseCase.finish(studyId, member.getMember().getId());

		return new ResponseEntity<>(
			ApiResponse.success(SuccessCode.UPDATE_SUCCESS),
			HttpStatus.OK);
	}
}
