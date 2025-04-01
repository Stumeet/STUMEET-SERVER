package com.stumeet.server.study.adapter.out.persistance.mapper;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.domain.StudyMeetingSchedule;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudyMeetingSchedulePersistenceMapper {

	private final MeetingRepetitionPersistenceMapper meetingRepetitionPersistenceMapper;

	public StudyMeetingSchedule toDomain(LocalTime meetingTime, String meetingRepetition) {
		return StudyMeetingSchedule.of(meetingTime, meetingRepetitionPersistenceMapper.toDomain(meetingRepetition));
	}
}
