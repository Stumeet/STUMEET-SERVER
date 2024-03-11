package com.stumeet.server.study.adapter.out.persistance.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyFieldJpaEntity;
import com.stumeet.server.study.domain.StudyField;

@Component
public class StudyFieldPersistenceMapper {

	public StudyFieldJpaEntity toEntity(StudyField domain) {
		return StudyFieldJpaEntity.builder()
			.id(domain.getId())
			.name(domain.getName())
			.build();
	}

	public StudyField toDomain(StudyFieldJpaEntity entity) {
		return StudyField.builder()
			.id(entity.getId())
			.name(entity.getName())
			.build();
	}
}
