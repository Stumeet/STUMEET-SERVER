package com.stumeet.server.study.adapter.out.persistance.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.domain.Repetition;
import com.stumeet.server.study.domain.RepetitionType;

@Component
public class MeetingRepetitionPersistenceMapper {

	private static final String REPEAT_DELIMITER = ";";

	public Repetition toDomain(String meetingRepetition) {
		List<String> repetitionElements = List.of(meetingRepetition.split(REPEAT_DELIMITER));

		return Repetition.of(
			RepetitionType.valueOf(repetitionElements.getFirst()),
			repetitionElements.subList(1, repetitionElements.size()));
	}

	public String toColumn(Repetition repetition) {
		String dates = !repetition.getDates().isEmpty()
				? String.join(REPEAT_DELIMITER, repetition.getDates())
				: "";

		return repetition.getType().toString()
				+ REPEAT_DELIMITER
				+ dates;
	}
}
