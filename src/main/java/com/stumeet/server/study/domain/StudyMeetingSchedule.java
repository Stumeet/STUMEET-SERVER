package com.stumeet.server.study.domain;

import java.time.LocalTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StudyMeetingSchedule {

	private final LocalTime time;
	private final Repetition repetition;

	public static StudyMeetingSchedule of(LocalTime meetingTime, Repetition repetition) {
		return StudyMeetingSchedule.builder()
			.time(meetingTime)
			.repetition(repetition)
			.build();
	}

	public LocalTime getTime() {
		return time;
	}

	public RepetitionType getRepetitionType() {
		return repetition.getType();
	}

	public List<String> getRepetitionDates() {
		return repetition.getDates();
	}
}