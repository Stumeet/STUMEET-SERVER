package com.stumeet.server.study.adapter.out.persistance.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.domain.RepetitionType;
import com.stumeet.server.study.domain.StudyMeetingSchedule;

@Component
public class MeetingRepetitionPersistenceMapper {

	private static final String REPEAT_DELIMITER = ";";

	public StudyMeetingSchedule.Repetition toDomain(String meetingRepetition) {
		List<String> repetitionElements = List.of(meetingRepetition.split(REPEAT_DELIMITER));

		return StudyMeetingSchedule.Repetition.builder()
				.type(RepetitionType.valueOf(repetitionElements.getFirst()))
				.dates(repetitionElements.subList(1, repetitionElements.size()))
				.build();
	}

	public String toColumn(StudyMeetingSchedule.Repetition repetition) {
		String dates = repetition.getDates() != null
				? String.join(REPEAT_DELIMITER, repetition.getDates())
				: null;

		return repetition.getType().toString()
				+ REPEAT_DELIMITER
				+ dates;
	}
}
