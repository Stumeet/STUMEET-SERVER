package com.stumeet.server.study.domain;

import com.stumeet.server.study.domain.exception.StudyStatusNotExistsException;

public enum StudyStatus {

	ACTIVE,
	FINISHED;

	public static StudyStatus fromValue(String value) {
		try {
			return StudyStatus.valueOf(value);
		} catch (IllegalArgumentException exception) {
			throw new StudyStatusNotExistsException(value);
		}
	}
}
