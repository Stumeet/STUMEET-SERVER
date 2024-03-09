package com.stumeet.server.study.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.study.adapter.in.web.response.StudyDetailResponse;
import com.stumeet.server.study.application.port.in.StudyQueryUseCase;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1/studies")
@RequiredArgsConstructor
public class StudyQueryApi {

	private final StudyQueryUseCase studyQueryUseCase;

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<StudyDetailResponse>> getStudy(
		@PathVariable(name = "id") Long id
	) {
		StudyDetailResponse response = studyQueryUseCase.getStudyDetailById(id);
		return ResponseEntity.ok(ApiResponse.success(SuccessCode.GET_SUCCESS, response));
	}
}
