package com.stumeet.server.study.domain;

import java.time.LocalTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StudyMeetingSchedule {

	private final LocalTime time;
	private final Repetition repetition;

	@Getter
	@AllArgsConstructor(staticName = "of")
	public static class Repetition {

		private final RepetitionType type;

		private final List<String> dates;
	}

	public static StudyMeetingSchedule of(LocalTime time, Repetition repetition) {
		return new StudyMeetingSchedule(time, repetition);
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