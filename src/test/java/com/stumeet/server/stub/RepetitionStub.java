package com.stumeet.server.stub;

import java.util.List;

import com.stumeet.server.study.domain.Repetition;
import com.stumeet.server.study.domain.RepetitionType;

public class RepetitionStub {

	public static Repetition getDailyRepetition() {
		return Repetition.of(RepetitionType.DAILY, null);
	}

	public static Repetition getWeeklyRepetition() {
		return Repetition.of(RepetitionType.WEEKLY, List.of("수", "금"));
	}

	public static Repetition getMonthlyRepetition() {
		return Repetition.of(RepetitionType.MONTHLY, List.of("10", "20", "30"));
	}
}
