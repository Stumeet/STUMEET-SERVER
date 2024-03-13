package com.stumeet.server.study.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class StudyTags {

	private static final String TOPIC_DELIMITER = ";";

	private final List<String> tags;

	private StudyTags(List<String> tags) {
		this.tags = tags;
	}

	public static StudyTags from(String rawTags) {
		List<String> tags = splitTopicsIntoList(rawTags);
		return new StudyTags(tags);
	}

	private static List<String> splitTopicsIntoList(String rawTags) {
		if (rawTags != null) {
			return Arrays.stream(rawTags.split(TOPIC_DELIMITER)).toList();
		}
		return Collections.emptyList();
	}

	protected String assemble() {
		return String.join(";", tags);
	}
}
