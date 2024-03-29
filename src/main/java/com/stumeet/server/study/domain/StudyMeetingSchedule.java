package com.stumeet.server.study.domain;

import java.time.LocalTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class StudyMeetingSchedule {

	private static final String REPEAT_DELIMITER = ";";

	private final LocalTime time;
	private final Repeat repeat;

	// 구분자 ;으로 연결된 형식
	// 첫번째 요소: 반복 유형
	// 이후: 반복 날짜/요일
	private StudyMeetingSchedule(LocalTime time, String delimitedRepeat) {
		List<String> repeats = List.of(delimitedRepeat.split(REPEAT_DELIMITER));
		ScheduleRepeatType repeatType = ScheduleRepeatType.valueOf(repeats.getFirst());

		this.time = time;
		this.repeat = new Repeat(repeatType, repeats.subList(1, repeats.size()));
	}

	public static StudyMeetingSchedule of(LocalTime time, String delimitedRepeat) {
		return new StudyMeetingSchedule(time, delimitedRepeat);
	}

	public LocalTime getTime() {
		return time;
	}

	public ScheduleRepeatType getRepeatType() {
		return repeat.getRepeatType();
	}

	public List<String> getRepeatDays() {
		return repeat.getRepeatDays();
	}

	public String getDelimitedRepeat() {
		return repeat.getRepeatType().toString()
				+ REPEAT_DELIMITER
				+ String.join(REPEAT_DELIMITER, repeat.getRepeatDays());
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	private class Repeat {

		private final ScheduleRepeatType repeatType;

		private final List<String> repeatDays;
	}
}