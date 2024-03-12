package com.stumeet.server.study.adapter.in.web.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record StudyDetailResponse(
	Long id,
	String field,
	String name,
	List<String> topics,
	String intro,
	String region,
	String rule,
	String mainImage,
	int headCount,
	LocalDateTime startDate,
	LocalDateTime endDate,
	boolean isFinished,
	boolean isDeleted
) {
}
