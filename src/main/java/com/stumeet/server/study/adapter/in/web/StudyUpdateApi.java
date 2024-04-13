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
import com.stumeet.server.study.application.port.in.StudyUpdateUseCase;
import com.stumeet.server.study.application.port.in.command.StudyUpdateCommand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/api/v1/studies")
public class StudyUpdateApi {

	private final StudyUpdateUseCase studyUpdateUseCase;

	@PatchMapping("/{studyId}")
	public ResponseEntity<ApiResponse<Void>> update(
		@AuthenticationPrincipal LoginMember member,
		@PathVariable Long studyId,
		@Valid StudyUpdateCommand request
	) {
		studyUpdateUseCase.update(studyId, member.getMember().getId(), request);

		return new ResponseEntity<>(
			ApiResponse.success(SuccessCode.UPDATE_SUCCESS),
			HttpStatus.CREATED);
	}
}
