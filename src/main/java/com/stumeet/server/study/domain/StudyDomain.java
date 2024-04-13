package com.stumeet.server.study.domain;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyDomain {

	private StudyField studyField;

	private List<String> studyTags;

	public String getStudyFieldName() {
		return studyField.getName();
	}
}
