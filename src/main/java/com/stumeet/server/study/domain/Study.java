package com.stumeet.server.study.domain;

import com.stumeet.server.file.application.port.out.FileUrl;

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

	private static final int INITIAL_STUDY_HEAD_COUNT = 1;

	private Long id;

	private String name;

	private StudyDomain studyDomain;

	private String region;

	private StudyMeetingSchedule meetingSchedule;

	private String intro;

	private String rule;

	private StudyPeriod period;

	private String imageUrl;

	private boolean isFinished;

	private boolean isDeleted;

	public static Study create(StudyDomain studyDomain, String name, String intro, String rule,
		String region, StudyPeriod studyPeriod, StudyMeetingSchedule meetingSchedule) {
		return Study.builder()
			.studyDomain(studyDomain)
			.name(name)
			.intro(intro)
			.rule(rule)
			.region(region)
			.period(studyPeriod)
			.meetingSchedule(meetingSchedule)
			.isFinished(false)
			.isDeleted(false)
			.build();
	}

	public void setImageUrl(FileUrl fileUrl) {
		imageUrl = fileUrl.url();
	}

	public String getStudyFieldName() {
		return studyDomain.getStudyFieldName();
	}

	public List<String> getStudyTags() {
		return studyDomain.getStudyTags();
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