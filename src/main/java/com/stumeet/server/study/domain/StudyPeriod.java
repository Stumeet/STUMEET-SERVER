package com.stumeet.server.study.domain;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StudyPeriod {

	private LocalDate startDate;

	private LocalDate endDate;
}
