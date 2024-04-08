package com.stumeet.server.study.application.port.in.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.domain.StudyField;

@Component
public class StudyFieldUseCaseMapper {

	public StudyField toDomain(Long studyFieldId) {
		return StudyField.builder()
				.id(studyFieldId)
				.build();
	}
}
