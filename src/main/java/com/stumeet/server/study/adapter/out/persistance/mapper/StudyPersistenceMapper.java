package com.stumeet.server.study.adapter.out.persistance.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.study.domain.StudyPeriod;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudyPersistenceMapper {

	private final StudyDomainPersistenceMapper studyDomainPersistenceMapper;
	private final StudyTagPersistenceMapper studyTagPersistenceMapper;
	private final StudyMeetingSchedulePersistenceMapper studyMeetingSchedulePersistenceMapper;
	private final MeetingRepetitionPersistenceMapper meetingRepetitionPersistenceMapper;

	public Study toDomain(StudyJpaEntity entity) {
		return Study.builder()
			.id(entity.getId())
			.name(entity.getName())
			.studyDomain(studyDomainPersistenceMapper.toDomain(entity))
			.region(entity.getRegion())
			.intro(entity.getIntro())
			.rule(entity.getRule())
			.headcount(entity.getHeadcount())
			.period(StudyPeriod.of(entity.getStartDate(), entity.getEndDate()))
			.imageUrl(entity.getImage())
			.meetingSchedule(
				studyMeetingSchedulePersistenceMapper.toDomain(entity.getMeetingTime(), entity.getMeetingRepetition()))
			.isFinished(entity.getIsFinished())
			.isDeleted(entity.getIsDeleted())
			.build();
	}

	public List<Study> toDomains(List<StudyJpaEntity> studyJpaEntities) {
		return studyJpaEntities.stream()
			.map(this::toDomain)
			.toList();
	}

	public StudyJpaEntity toEntity(Study domain) {
		return StudyJpaEntity.builder()
			.id(domain.getId())
			.name(domain.getName())
			.studyField(domain.getStudyDomain().getStudyField())
			.studyTags(studyTagPersistenceMapper.toEntities(domain.getStudyTags(), domain.getId()))
			.region(domain.getRegion())
			.intro(domain.getIntro())
			.rule(domain.getRule())
			.headcount(domain.getHeadcount())
			.startDate(domain.getStartDate())
			.endDate(domain.getEndDate())
			.image(domain.getImageUrl())
			.meetingTime(domain.getMeetingSchedule().getTime())
			.meetingRepetition(meetingRepetitionPersistenceMapper.toColumn(domain.getMeetingSchedule().getRepetition()))
			.isFinished(domain.isFinished())
			.isDeleted(domain.isDeleted())
			.build();
	}
}
