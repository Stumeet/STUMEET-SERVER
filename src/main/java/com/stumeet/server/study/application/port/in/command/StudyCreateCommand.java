package com.stumeet.server.study.application.port.in.command;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.stumeet.server.study.domain.RepetitionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudyCreateCommand(
		@NotNull
		Long studyFieldId,
		@NotBlank
		String name,
		@NotBlank
		String intro,
		@NotBlank
		String region,
		String rule,
		@NotNull
		LocalDateTime startDate,
		@NotNull
		LocalDateTime endDate,
		@NotNull
		LocalTime meetingTime,
		@NotNull
		RepetitionType meetingRepetitionType,
		List<String> meetingRepetitionDates,
		List<String> studyTags
) {
}
