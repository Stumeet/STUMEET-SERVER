package com.stumeet.server.study.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StudyPeriod {

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	public static StudyPeriod of(LocalDateTime startDate, LocalDateTime endDate) {
		return new StudyPeriod(startDate, endDate);
	}
}
