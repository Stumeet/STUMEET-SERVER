package com.stumeet.server.study.adapter.out.persistance.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyTagJpaEntity;

@Component
public class StudyTagPersistenceMapper {

	public List<String> toDomains(List<StudyTagJpaEntity> entities) {
		return entities.stream()
			.map(StudyTagJpaEntity::getName)
			.toList();
	}

	public List<StudyTagJpaEntity> toEntities(List<String> domains, Long studyId) {
		return domains.stream()
			.map(domain -> StudyTagJpaEntity.builder()
				.studyId(studyId)
				.name(domain)
				.build())
			.toList();
	}
}
