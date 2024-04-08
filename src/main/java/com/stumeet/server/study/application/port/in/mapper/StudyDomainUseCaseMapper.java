package com.stumeet.server.study.application.port.in.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.domain.StudyDomain;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudyDomainUseCaseMapper {

	private final StudyFieldUseCaseMapper studyFieldUseCaseMapper;
	private final StudyTagsUseCaseMapper studyTagsUseCaseMapper;

	public StudyDomain toDomain(StudyCreateCommand command) {
		return StudyDomain.builder()
				.studyField(studyFieldUseCaseMapper.toDomain(command.studyFieldId()))
				.studyTags(studyTagsUseCaseMapper.toDomains(command.studyTags()))
				.build();
	}
}
