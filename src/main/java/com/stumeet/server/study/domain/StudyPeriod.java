package com.stumeet.server.study.domain;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StudyPeriod {

	private LocalDate startDate;

	private LocalDate endDate;

	public static StudyPeriod of(LocalDate startDate, LocalDate endDate) {
		return new StudyPeriod(startDate, endDate);
	}
}
