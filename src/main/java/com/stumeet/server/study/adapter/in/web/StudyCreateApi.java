package com.stumeet.server.study.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.annotation.validator.NullOrImageFile;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.study.application.port.in.StudyCreateUseCase;
import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1/studies")
@RequiredArgsConstructor
public class StudyCreateApi {

	private final StudyCreateUseCase studyCreateUseCase;

	@PostMapping
	public ResponseEntity<ApiResponse<Void>> create(
		@AuthenticationPrincipal LoginMember member,
		@RequestPart @Valid StudyCreateCommand request,
		@RequestPart(required = false) @Valid @NullOrImageFile MultipartFile mainImageFile
	) {
		studyCreateUseCase.create(member.getMember().getId(), request, mainImageFile);

		return new ResponseEntity<>(
			ApiResponse.success(SuccessCode.STUDY_CREATE_SUCCESS),
			HttpStatus.CREATED);
	}
}
