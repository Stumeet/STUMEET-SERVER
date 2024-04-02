package com.stumeet.server.study.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Study {

	private Long id;

	private String name;

	private StudyDomain studyDomain;

	private String region;

	private StudyMeetingSchedule meetingSchedule;

	private String intro;

	private String rule;

	private StudyPeriod period;

	private StudyHeadCount headcount;

	private String image;

	private boolean isFinished;

	private boolean isDeleted;

	public String getStudyFieldName() {
		return studyDomain.getStudyFieldName();
	}

	public List<String> getStudyTagNames() {
		return studyDomain.getStudyTagNames();
	}

	public int getHeadcountNumber() {
		return headcount.getNumber();
	}

	public LocalDate getStartDate() {
		return period.getStartDate();
	}

	public LocalDate getEndDate() {
		return period.getEndDate();
	}

	public String getMeetingRepeatTypeName() {
		return meetingSchedule.getRepetitionType().name();
	}

	public List<String> getRepetitionDates() {
		return meetingSchedule.getRepetitionDates();
	}

	public LocalTime getMeetingTime() {
		return meetingSchedule.getTime();
	}
}