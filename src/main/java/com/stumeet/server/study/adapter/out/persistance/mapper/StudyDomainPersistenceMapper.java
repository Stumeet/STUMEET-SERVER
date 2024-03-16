package com.stumeet.server.study.adapter.out.persistance.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyDomainJpaEntity;
import com.stumeet.server.study.domain.StudyDomain;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudyDomainPersistenceMapper {

	private final StudyFieldPersistenceMapper studyFieldPersistenceMapper;
	private final StudyTagPersistenceMapper studyTagPersistenceMapper;

	public StudyDomain toDomain(StudyDomainJpaEntity entity) {
		return StudyDomain.builder()
				.id(entity.getId())
				.studyField(studyFieldPersistenceMapper.toDomain(entity.getStudyField()))
				.studyTags(studyTagPersistenceMapper.toDomains(entity.getStudyTags()))
				.build();
	}

	public StudyDomainJpaEntity toEntity(StudyDomain domain) {
		return StudyDomainJpaEntity.builder()
				.id(domain.getId())
				.studyField(studyFieldPersistenceMapper.toEntity(domain.getStudyField()))
				.studyTags(studyTagPersistenceMapper.toEntities(domain.getStudyTags(), domain.getId()))
				.build();
	}
}
