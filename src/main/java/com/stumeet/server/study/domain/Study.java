package com.stumeet.server.study.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;

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

	private String image;

	private boolean isFinished;

	private boolean isDeleted;

	public static Study create(StudyCreateCommand command, StudyDomain studyDomain, String imageUrl) {
		StudyMeetingSchedule meetingSchedule =
				StudyMeetingSchedule.builder()
						.time(command.meetingTime())
						.repetition(StudyMeetingSchedule.Repetition.builder()
								.type(command.meetingRepetitionType())
								.dates(command.meetingRepetitionDates())
								.build())
						.build();

		StudyPeriod studyPeriod = StudyPeriod.builder()
				.startDate(command.startDate())
				.endDate(command.endDate())
				.build();


		return Study.builder()
				.studyDomain(studyDomain)
				.name(command.name())
				.intro(command.intro())
				.rule(command.rule())
				.region(command.region())
				.period(studyPeriod)
				.meetingSchedule(meetingSchedule)
				.image(imageUrl)
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