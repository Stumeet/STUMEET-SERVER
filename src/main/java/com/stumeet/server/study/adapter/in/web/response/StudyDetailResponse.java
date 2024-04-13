package com.stumeet.server.study.adapter.in.web.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Builder;

@Builder
public record StudyDetailResponse(
	Long id,
	String field,
	String name,
	List<String> tags,
	String intro,
	String region,
	String rule,
	String image,
	int headcount,
	LocalDate startDate,
	LocalDate endDate,
	LocalTime meetingTime,
	String meetingRepetitionType,
	List<String> meetingRepetitionDates,
	boolean isFinished,
	boolean isDeleted
) {
}
