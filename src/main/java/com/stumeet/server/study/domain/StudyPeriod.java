package com.stumeet.server.study.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class StudyPeriod {

	private LocalDate startDate;

	private LocalDate endDate;
}
