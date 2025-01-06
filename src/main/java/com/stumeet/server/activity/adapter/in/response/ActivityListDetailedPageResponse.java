package com.stumeet.server.activity.adapter.in.response;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ActivityListDetailedPageResponse(
		Long id,
		Long studyId,
		String studyName,
		String category,
		String title,
		String content,
		LocalDateTime startDate,
		LocalDateTime endDate,
		String location,
		ActivityParticipantSimpleResponse author,
		LocalDateTime createdAt
) {
}
