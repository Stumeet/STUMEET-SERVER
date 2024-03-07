package com.stumeet.server.study.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyQueryUseCase;
import com.stumeet.server.study.application.port.out.StudyQueryPort;
import com.stumeet.server.study.domain.Study;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class StudyQueryService implements StudyQueryUseCase {

	private final StudyQueryPort studyQueryPort;

	@Override
	public Study getById(Long id) {
		return studyQueryPort.getById(id);
	}
}
