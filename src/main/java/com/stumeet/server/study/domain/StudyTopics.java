package com.stumeet.server.study.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class StudyTopics {

	private static final String TOPIC_DELIMITER = ";";

	private final List<String> topics;

	private StudyTopics(List<String> topics) {
		this.topics = topics;
	}

	public static StudyTopics from(String rawTopics) {
		List<String> topics = splitTopicsIntoList(rawTopics);
		return new StudyTopics(topics);
	}

	private static List<String> splitTopicsIntoList(String rawTopics) {
		if (rawTopics != null) {
			return Arrays.stream(rawTopics.split(TOPIC_DELIMITER)).toList();
		}
		return Collections.emptyList();
	}

	protected String assemble() {
		return String.join(";", topics);
	}
}
