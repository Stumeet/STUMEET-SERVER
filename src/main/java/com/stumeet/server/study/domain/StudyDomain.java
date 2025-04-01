package com.stumeet.server.study.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class StudyDomain {

	private final StudyField studyField;

	private final List<String> studyTags;

	public String getStudyFieldName() {
		return studyField.getName();
	}
}
