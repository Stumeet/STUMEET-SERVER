package com.stumeet.server.study.application.port.in.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.domain.StudyMeetingSchedule;

@Component
public class MeetingScheduleUseCaseMapper {

	public StudyMeetingSchedule toDomain(StudyCreateCommand command) {
		return StudyMeetingSchedule.of(
				command.meetingTime(),
				StudyMeetingSchedule.Repetition.of(
						command.meetingRepetitionType(),
						command.meetingRepetitionDates()));
	}
}