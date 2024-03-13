package com.stumeet.server.study.domain;

import java.time.LocalDateTime;

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

	private StudyField studyField;

	private StudyTags studyTags;

	private String region;

	private String intro;

	private String rule;

	private StudyPeriod period;

	private StudyHeadCount headcount;

	private String image;

	private Boolean isFinished;

	private Boolean isDeleted;

	public String getStudyFieldName() {
		return studyField.getName();
	}

	public String getAssembledTags() {
		return studyTags.assemble();
	}

	public int getHeadcountNumber() {
		return headcount.getNumber();
	}

	public LocalDateTime getStartDate() {
		return period.getStartDate();
	}

	public LocalDateTime getEndDate() {
		return period.getEndDate();
	}
}

