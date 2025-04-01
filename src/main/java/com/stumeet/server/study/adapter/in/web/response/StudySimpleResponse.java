package com.stumeet.server.study.adapter.in.web.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;

@Builder
public record StudySimpleResponse(
	Long id,
	String name,
	String field,
	List<String> tags,
	String image,
	int headcount,
	LocalDate startDate,
	LocalDate endDate
) {
}
