package com.stumeet.server.study.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.adapter.in.web.response.StudyDetailResponse;
import com.stumeet.server.study.application.port.in.StudyQueryUseCase;
import com.stumeet.server.study.application.port.out.StudyQueryPort;
import com.stumeet.server.study.application.port.out.mapper.StudyUseCaseMapper;
import com.stumeet.server.study.domain.Study;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class StudyQueryService implements StudyQueryUseCase {

	private final StudyQueryPort studyQueryPort;
	private final StudyUseCaseMapper studyUseCaseMapper;

	@Override
	@Transactional(readOnly = true)
	public StudyDetailResponse getStudyDetailById(Long id) {
		Study study = studyQueryPort.getById(id);
		return studyUseCaseMapper.toStudyDetailResponse(study);
	}
}
