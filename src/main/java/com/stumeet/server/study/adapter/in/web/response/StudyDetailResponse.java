package com.stumeet.server.study.adapter.in.web.response;

import java.time.LocalDateTime;
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
	LocalDateTime startDate,
	LocalDateTime endDate,
	boolean isFinished,
	boolean isDeleted
) {
}