package com.stumeet.server.study.domain;

import java.util.List;

import com.stumeet.server.study.domain.exception.InvalidRepetitionDatesException;

import lombok.Getter;

@Getter
public class Repetition {

	private final RepetitionType type;
	private final List<String> dates;

	private Repetition(RepetitionType type, List<String> dates) {
		validateRepetition(type, dates);
		this.type = type;
		this.dates = type.equals(RepetitionType.DAILY) ? List.of() : dates;
	}

	private void validateRepetition(RepetitionType type, List<String> dates) {
		if (!RepetitionType.DAILY.equals(type) && dates.isEmpty()) {
			throw new InvalidRepetitionDatesException(type.toString());
		}
	}

	public static Repetition of(RepetitionType type, List<String> dates) {
		return new Repetition(type, dates);
	}
}