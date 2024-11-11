package com.stumeet.server.study.application.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.adapter.in.web.response.JoinedStudiesResponse;
import com.stumeet.server.study.adapter.in.web.response.StudyDetailResponse;
import com.stumeet.server.study.application.port.in.StudyQueryUseCase;
import com.stumeet.server.study.application.port.in.command.GetJoinedStudyCommand;
import com.stumeet.server.study.application.port.out.StudyQueryPort;
import com.stumeet.server.study.application.port.in.mapper.StudyUseCaseMapper;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.study.domain.StudyStatus;
import com.stumeet.server.study.domain.exception.StudyStatusNotExistsException;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyQueryService implements StudyQueryUseCase {

	private final StudyQueryPort studyQueryPort;
	private final StudyUseCaseMapper studyUseCaseMapper;

	@Override
	public StudyDetailResponse getStudyDetailById(Long id) {
		Study study = studyQueryPort.getById(id);
		return studyUseCaseMapper.toStudyDetailResponse(study);
	}

	@Override
	public JoinedStudiesResponse getJoinedStudiesByStatus(GetJoinedStudyCommand command) {
		StudyStatus status = command.studyStatus();

		switch (status) {
			case ACTIVE -> {
				List<Study> activeJoinedStudies = studyQueryPort.getMemberRecentActiveStudies(command.memberId());
				return studyUseCaseMapper.toJoinedStudiesResponse(activeJoinedStudies);
			}

			case FINISHED -> {
				List<Study> legacyStudies = studyQueryPort.getMemberLegacyStudies(command.memberId());
				return studyUseCaseMapper.toJoinedStudiesResponse(legacyStudies);
			}

			default -> throw new StudyStatusNotExistsException(status.name());
		}
	}

	@Override
	public String getStudyName(Long id) {
		Study study = studyQueryPort.getById(id);
		return study.getName();
	}
}
