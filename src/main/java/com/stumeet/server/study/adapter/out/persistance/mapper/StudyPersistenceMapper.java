package com.stumeet.server.study.adapter.out.persistance.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.study.domain.StudyMeetingSchedule;
import com.stumeet.server.study.domain.StudyPeriod;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudyPersistenceMapper {

	private final StudyDomainPersistenceMapper studyDomainPersistenceMapper;
	private final MeetingRepetitionPersistenceMapper meetingRepetitionPersistenceMapper;
	private final StudyTagPersistenceMapper studyTagPersistenceMapper;

	public Study toDomain(StudyJpaEntity entity) {
		return Study.builder()
				.id(entity.getId())
				.name(entity.getName())
				.studyDomain(studyDomainPersistenceMapper.toDomain(entity))
				.region(entity.getRegion())
				.intro(entity.getIntro())
				.rule(entity.getRule())
				.period(StudyPeriod.builder()
						.startDate(entity.getStartDate())
						.endDate(entity.getEndDate())
						.build())
				.imageUrl(entity.getImage())
				.meetingSchedule(StudyMeetingSchedule.builder()
						.time(entity.getMeetingTime())
						.repetition(meetingRepetitionPersistenceMapper.toDomain(entity.getMeetingRepetition()))
						.build())
				.isFinished(entity.getIsFinished())
				.isDeleted(entity.getIsDeleted())
				.build();
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
				.startDate(domain.getStartDate())
				.endDate(domain.getEndDate())
				.image(domain.getImageUrl())
				.meetingTime(domain.getMeetingSchedule().getTime())
				.meetingRepetition(
						meetingRepetitionPersistenceMapper.toColumn(domain.getMeetingSchedule().getRepetition()))
				.isFinished(domain.isFinished())
				.isDeleted(domain.isDeleted())
				.build();
	}
}
