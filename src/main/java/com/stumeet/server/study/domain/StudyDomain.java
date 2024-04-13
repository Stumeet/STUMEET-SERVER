package com.stumeet.server.study.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class StudyDomain {

	private final StudyField studyField;

	private final List<String> studyTags;

	private StudyDomain(StudyField studyField, List<String> studyTags) {
		this.studyField = studyField;
		this.studyTags = studyTags != null ? studyTags : List.of();
	}

	public static StudyDomain of(StudyField studyField, List<String> studyTags) {
		return new StudyDomain(studyField, studyTags);
	}

	public String getStudyFieldName() {
		return studyField.getName();
	}
}
