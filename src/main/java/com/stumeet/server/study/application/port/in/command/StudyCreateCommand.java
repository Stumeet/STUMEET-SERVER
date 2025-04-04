package com.stumeet.server.study.application.port.in.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.stumeet.server.common.annotation.validator.NullOrNotBlank;
import com.stumeet.server.study.domain.RepetitionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudyCreateCommand(

		@NotBlank(message = "스터디 분야를 입력해주세요.")
		String studyField,
		@NotBlank(message = "이름을 입력해주세요.")
		String name,
		@NotBlank(message = "소개글을 입력해주세요.")
		String intro,
		@NotBlank(message = "지역을 입력해주세요.")
		String region,
		@NullOrNotBlank(message = "규칙은 공백일 수 없습니다.")
		String rule,
		@NotNull(message = "시작일을 입력해주세요.")
		LocalDate startDate,
		@NotNull(message = "종료일을 입력해주세요.")
		LocalDate endDate,
		@NotNull(message = "정기모임 시간을 입력해주세요.")
		LocalTime meetingTime,
		@NotNull(message = "정기모임 반복 유형을 입력해주세요.")
		RepetitionType meetingRepetitionType,
		@NotNull(message = "정기모임 반복 일정을 입력해주세요.")
		List<String> meetingRepetitionDates,
		@NotNull(message = "스터디 태그를 입력해주세요.")
		List<String> studyTags
) {
}
