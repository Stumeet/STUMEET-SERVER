package com.stumeet.server.activity.adapter.in.response;

import java.time.LocalDateTime;

import com.stumeet.server.activity.domain.model.ActivityCategory;

import lombok.Builder;

@Builder
public record ActivityListDetailedPageResponse(
		Long id,
		ActivityCategory category,
		String title,
		String content,
		LocalDateTime startDate,
		LocalDateTime endDate,
		String location,
		ActivityParticipantSimpleResponse author,
		LocalDateTime createdAt
) {
}
