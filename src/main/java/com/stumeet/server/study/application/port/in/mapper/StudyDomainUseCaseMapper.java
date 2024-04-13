package com.stumeet.server.study.application.port.in.mapper;

import com.stumeet.server.study.domain.StudyField;
import org.springframework.stereotype.Component;

import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.domain.StudyDomain;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudyDomainUseCaseMapper {

	public StudyDomain toDomain(StudyCreateCommand command) {
		return StudyDomain.builder()
				.studyField(StudyField.getByName(command.studyField()))
				.studyTags(command.studyTags())
				.build();
	}
}
