package com.stumeet.server.study.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.study.application.port.in.StudyCreateUseCase;
import com.stumeet.server.study.application.port.in.StudyQueryUseCase;
import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.application.port.in.response.StudyDetailResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1/studies")
@RequiredArgsConstructor
public class StudyCreateApi {

	private final StudyCreateUseCase studyCreateUseCase;
	private final StudyQueryUseCase studyQueryUseCase;

	public ResponseEntity<ApiResponse<StudyDetailResponse>> create(
			@AuthenticationPrincipal LoginMember member,
			@Valid StudyCreateCommand request
	) {
		Long createdStudyId = studyCreateUseCase.create(request, member.getMember());
		StudyDetailResponse response = studyQueryUseCase.getStudyDetailById(createdStudyId);

		return new ResponseEntity<>(
				ApiResponse.success(HttpStatus.CREATED.value(), "스터디 그룹 생성에 성공했습니다.", response),
				HttpStatus.CREATED);
	}
}
