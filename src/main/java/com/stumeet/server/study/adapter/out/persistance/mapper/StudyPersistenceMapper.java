package com.stumeet.server.study.adapter.out.persistance.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.study.domain.StudyHeadCount;
import com.stumeet.server.study.domain.StudyPeriod;
import com.stumeet.server.study.domain.StudyTopics;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudyPersistenceMapper {

	private final StudyFieldPersistenceMapper studyFieldPersistenceMapper;

	public StudyJpaEntity toEntity(Study domain) {
		return StudyJpaEntity.builder()
			.id(domain.getId())
			.name(domain.getName())
			.studyField(studyFieldPersistenceMapper.toEntity(domain.getStudyField()))
			.topics(domain.getAssembledTopics())
			.region(domain.getRegion())
			.intro(domain.getIntro())
			.rule(domain.getRule())
			.startDate(domain.getStartDate())
			.endDate(domain.getEndDate())
			.headCount(domain.getHeadCountNumber())
			.mainImage(domain.getMainImage())
			.isFinished(domain.getIsFinished())
			.isDeleted(domain.getIsDeleted())
			.build();
	}

	public Study toDomain(StudyJpaEntity entity) {
		return Study.builder()
			.id(entity.getId())
			.name(entity.getName())
			.studyField(studyFieldPersistenceMapper.toDomain(entity.getStudyField()))
			.studyTopics(StudyTopics.from(entity.getTopics()))
			.region(entity.getRegion())
			.intro(entity.getIntro())
			.rule(entity.getRule())
			.period(StudyPeriod.of(entity.getStartDate(), entity.getEndDate()))
			.headCount(StudyHeadCount.from(entity.getHeadCount()))
			.mainImage(entity.getMainImage())
			.isFinished(entity.getIsFinished())
			.isDeleted(entity.getIsDeleted())
			.build();
	}
}
