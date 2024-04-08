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

	private Long id;

	private StudyField studyField;

	private List<StudyTag> studyTags;

	public String getStudyFieldName() {
		return studyField.getName();
	}

	public boolean isStudyTagNotEmpty() {
		return !studyTags.isEmpty();
	}

	public List<String> getStudyTagNames() {
		return studyTags.stream()
				.map(StudyTag::getName)
				.toList();
	}
}
