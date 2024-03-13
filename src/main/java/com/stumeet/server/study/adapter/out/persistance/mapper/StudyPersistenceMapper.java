package com.stumeet.server.study.adapter.out.persistance.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.study.domain.StudyHeadCount;
import com.stumeet.server.study.domain.StudyPeriod;
import com.stumeet.server.study.domain.StudyTags;

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
			.tags(domain.getAssembledTags())
			.region(domain.getRegion())
			.intro(domain.getIntro())
			.rule(domain.getRule())
			.startDate(domain.getStartDate())
			.endDate(domain.getEndDate())
			.headcount(domain.getHeadcountNumber())
			.image(domain.getImage())
			.isFinished(domain.getIsFinished())
			.isDeleted(domain.getIsDeleted())
			.build();
	}

	public Study toDomain(StudyJpaEntity entity) {
		return Study.builder()
			.id(entity.getId())
			.name(entity.getName())
			.studyField(studyFieldPersistenceMapper.toDomain(entity.getStudyField()))
			.studyTags(StudyTags.from(entity.getTags()))
			.region(entity.getRegion())
			.intro(entity.getIntro())
			.rule(entity.getRule())
			.period(StudyPeriod.of(entity.getStartDate(), entity.getEndDate()))
			.headcount(StudyHeadCount.from(entity.getHeadcount()))
			.image(entity.getImage())
			.isFinished(entity.getIsFinished())
			.isDeleted(entity.getIsDeleted())
			.build();
	}
}
