package com.stumeet.server.study.domain;

public enum ScheduleRepeatType {

	DAILY("매일"),
	WEEKLY("매주"),
	MONTHLY("매달");

	private final String name;

	private ScheduleRepeatType(String name) {
		this.name = name;
	}
}
