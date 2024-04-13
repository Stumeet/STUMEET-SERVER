package com.stumeet.server.study.domain;

import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;

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

	public static Study create(StudyCreateCommand command, String imageUrl) {
		return Study.builder()
			.studyDomain(StudyDomain.of(StudyField.getByName(command.studyField()), command.studyTags()))
			.name(command.name())
			.intro(command.intro())
			.rule(command.rule())
			.region(command.region())
			.period(StudyPeriod.of(command.startDate(), command.endDate()))
			.meetingSchedule(
				StudyMeetingSchedule.of(
					command.meetingTime(),
					Repetition.of(command.meetingRepetitionType(), command.meetingRepetitionDates())))
			.imageUrl(imageUrl)
			.isFinished(false)
			.isDeleted(false)
			.build();
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