package com.stumeet.server.study.domain;

import java.time.LocalTime;
import java.util.List;

import com.stumeet.server.study.domain.exception.InvalidRepetitionDatesException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StudyMeetingSchedule {

	private final LocalTime time;
	private final Repetition repetition;

	@Getter
	public static class Repetition {

		private final RepetitionType type;

		private final List<String> dates;

		@Builder
		private Repetition(RepetitionType type, List<String> dates) {
			validateRepetition(type, dates);
			this.type = type;
			this.dates = type.equals(RepetitionType.DAILY) ? null : dates;
		}

		private void validateRepetition(RepetitionType type, List<String> dates) {
			if(!type.equals(RepetitionType.DAILY) && dates == null) {
				throw new InvalidRepetitionDatesException(type.toString());
			}
		}
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