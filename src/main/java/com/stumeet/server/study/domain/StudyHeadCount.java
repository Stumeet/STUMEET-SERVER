package com.stumeet.server.study.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StudyHeadCount {

	private int number;

	public static StudyHeadCount from(int number) {
		return new StudyHeadCount(number);
	}
}

