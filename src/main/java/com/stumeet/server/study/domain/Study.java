package com.stumeet.server.study.domain;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.application.port.in.command.StudyUpdateCommand;

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

	private static final int INITIAL_STUDY_HEAD_COUNT = 0;

	private Long id;

	private String name;

	private StudyDomain studyDomain;

	private String region;

	private StudyMeetingSchedule meetingSchedule;

	private String intro;

	private String rule;

	private Integer headcount;

	private StudyPeriod period;

	private String imageUrl;

	private boolean isFinished;

	private boolean isDeleted;

	public static Study create(StudyCreateCommand command) {
		return Study.builder()
			.studyDomain(StudyDomain.of(StudyField.fromName(command.studyField()), command.studyTags()))
			.name(command.name())
			.intro(command.intro())
			.rule(command.rule())
			.region(command.region())
			.headcount(INITIAL_STUDY_HEAD_COUNT)
			.period(StudyPeriod.of(command.startDate(), command.endDate()))
			.meetingSchedule(
				StudyMeetingSchedule.of(
					command.meetingTime(),
					Repetition.of(command.meetingRepetitionType(), command.meetingRepetitionDates())))
			.isFinished(false)
			.isDeleted(false)
			.build();
	}

	public static Study updateInfo(StudyUpdateCommand command, Study existingStudy) {
		return Study.builder()
			.id(existingStudy.getId())
			.studyDomain(StudyDomain.of(StudyField.fromName(command.studyField()), command.studyTags()))
			.name(command.name())
			.intro(command.intro())
			.rule(command.rule())
			.region(command.region())
			.headcount(existingStudy.headcount)
			.period(StudyPeriod.of(command.startDate(), command.endDate()))
			.meetingSchedule(
				StudyMeetingSchedule.of(
					command.meetingTime(),
					Repetition.of(command.meetingRepetitionType(), command.meetingRepetitionDates())))
			.isFinished(existingStudy.isFinished)
			.isDeleted(existingStudy.isDeleted)
			.build();
	}

	public void updateMainImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isStudyTagChanged(List<String> studyTags) {
		return !getStudyTags().equals(studyTags);
	}

	public void finish(LocalDate today) {
		validateFinishPossible(today);

		period.determineEndDate(today);
		this.isFinished = true;
	}

	private void validateFinishPossible(LocalDate today) {
		if (period.isBeforeStartDate(today)) {
			throw new InvalidStateException(ErrorCode.START_DATE_NOT_YET_EXCEPTION);
		}
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