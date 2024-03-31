package com.stumeet.server.study.adapter.out.persistance.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyDomainJpaEntity;
import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.study.domain.StudyDomain;
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
		StudyDomain studyDomain = studyDomainPersistenceMapper.toDomain(entity.getStudyDomain());
		StudyPeriod studyPeriod = StudyPeriod.of(entity.getStartDate(), entity.getEndDate());
		StudyHeadCount studyHeadCount = StudyHeadCount.from(entity.getHeadcount());
		StudyMeetingSchedule studyMeetingSchedule = StudyMeetingSchedule
				.of(entity.getMeetingTime(), meetingRepetitionPersistenceMapper.toDomain(entity.getMeetingRepetition()));

		return Study.builder()
				.id(entity.getId())
				.name(entity.getName())
				.studyDomain(studyDomain)
				.region(entity.getRegion())
				.intro(entity.getIntro())
				.rule(entity.getRule())
				.period(studyPeriod)
				.headcount(studyHeadCount)
				.image(entity.getImage())
				.meetingSchedule(studyMeetingSchedule)
				.isFinished(entity.getIsFinished())
				.isDeleted(entity.getIsDeleted())
				.build();
	}

	public StudyJpaEntity toEntity(Study domain) {
		StudyDomainJpaEntity studyDomainJpaEntity = studyDomainPersistenceMapper.toEntity(domain.getStudyDomain());
		String meetingRepetition = meetingRepetitionPersistenceMapper.toColumn(domain.getMeetingSchedule().getRepetition());

		return StudyJpaEntity.builder()
				.id(domain.getId())
				.name(domain.getName())
				.studyDomain(studyDomainJpaEntity)
				.region(domain.getRegion())
				.intro(domain.getIntro())
				.rule(domain.getRule())
				.startDate(domain.getStartDate())
				.endDate(domain.getEndDate())
				.headcount(domain.getHeadcountNumber())
				.image(domain.getImage())
				.meetingTime(domain.getMeetingSchedule().getTime())
				.meetingRepetition(meetingRepetition)
				.isFinished(domain.isFinished())
				.isDeleted(domain.isDeleted())
				.build();
	}
}
