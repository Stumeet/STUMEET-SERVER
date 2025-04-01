package com.stumeet.server.study.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RepetitionType {

	DAILY("매일"),
	WEEKLY("매주"),
	MONTHLY("매달");

	private final String name;
}
