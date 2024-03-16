package com.stumeet.server.study.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
public class Study {

	private Long id;

	private String name;

	private StudyDomain studyDomain;

	private String region;

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

	public LocalDateTime getStartDate() {
		return period.getStartDate();
	}

	public LocalDateTime getEndDate() {
		return period.getEndDate();
	}
}

