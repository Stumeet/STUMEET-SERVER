package com.stumeet.server.stub;

import java.util.List;

import com.stumeet.server.study.domain.RepetitionType;
import com.stumeet.server.study.domain.StudyMeetingSchedule;

public class RepetitionStub {

	public static StudyMeetingSchedule.Repetition getDailyRepetition() {
		return StudyMeetingSchedule.Repetition.builder()
				.type(RepetitionType.DAILY)
				.dates(null)
				.build();
	}

	public static StudyMeetingSchedule.Repetition getWeeklyRepetition() {
		return StudyMeetingSchedule.Repetition.builder()
				.type(RepetitionType.WEEKLY)
				.dates(List.of("수", "금"))
				.build();
	}

	public static StudyMeetingSchedule.Repetition getMonthlyRepetition() {
		return StudyMeetingSchedule.Repetition.builder()
				.type(RepetitionType.MONTHLY)
				.dates(List.of("10", "20", "30"))
				.build();
	}
}
