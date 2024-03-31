package com.stumeet.server.study.adapter.out.persistance.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.study.domain.StudyHeadCount;
import com.stumeet.server.study.domain.StudyMeetingSchedule;
import com.stumeet.server.study.domain.StudyPeriod;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudyPersistenceMapper {

	private final StudyDomainPersistenceMapper studyDomainPersistenceMapper;
	private final MeetingRepetitionPersistenceMapper meetingRepetitionPersistenceMapper;

	public Study toDomain(StudyJpaEntity entity) {
		return Study.builder()
				.id(entity.getId())
				.name(entity.getName())
				.studyDomain(studyDomainPersistenceMapper.toDomain(entity.getStudyDomain()))
				.region(entity.getRegion())
				.intro(entity.getIntro())
				.rule(entity.getRule())
				.period(StudyPeriod.of(entity.getStartDate(), entity.getEndDate()))
				.headcount(StudyHeadCount.from(entity.getHeadcount()))
				.image(entity.getImage())
				.meetingSchedule(
						StudyMeetingSchedule.of(
								entity.getMeetingTime(),
								meetingRepetitionPersistenceMapper.toDomain(entity.getMeetingRepetition())))
				.isFinished(entity.getIsFinished())
				.isDeleted(entity.getIsDeleted())
				.build();
	}

	public StudyJpaEntity toEntity(Study domain) {
		return StudyJpaEntity.builder()
				.id(domain.getId())
				.name(domain.getName())
				.studyDomain(studyDomainPersistenceMapper.toEntity(domain.getStudyDomain()))
				.region(domain.getRegion())
				.intro(domain.getIntro())
				.rule(domain.getRule())
				.startDate(domain.getStartDate())
				.endDate(domain.getEndDate())
				.headcount(domain.getHeadcountNumber())
				.image(domain.getImage())
				.meetingTime(domain.getMeetingSchedule().getTime())
				.meetingRepetition(
						meetingRepetitionPersistenceMapper.toColumn(domain.getMeetingSchedule().getRepetition()))
				.isFinished(domain.isFinished())
				.isDeleted(domain.isDeleted())
				.build();
	}
}
