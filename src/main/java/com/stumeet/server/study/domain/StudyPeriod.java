package com.stumeet.server.study.domain;

import java.time.LocalDate;

import com.stumeet.server.common.exception.model.BadRequestException;
import com.stumeet.server.common.response.ErrorCode;

import lombok.Getter;

@Getter
public class StudyPeriod {

	private LocalDate startDate;

	private LocalDate endDate;

	private StudyPeriod(LocalDate startDate, LocalDate endDate) {
		validate(startDate, endDate);

		this.startDate = startDate;
		this.endDate = endDate;
	}

	private void validate(LocalDate startDate, LocalDate endDate) {
		if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
			throw new BadRequestException(ErrorCode.INVALID_STUDY_PERIOD_EXCEPTION);
		}
	}

	public static StudyPeriod of(LocalDate startDate, LocalDate endDate) {
		return new StudyPeriod(startDate, endDate);
	}

	public boolean isBeforeStartDate(LocalDate date) {
		return date.isBefore(startDate);
	}

	protected void determineEndDate(LocalDate date) {
		this.endDate = date;
	}
}
