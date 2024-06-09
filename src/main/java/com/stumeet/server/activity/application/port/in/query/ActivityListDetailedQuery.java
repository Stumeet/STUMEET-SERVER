package com.stumeet.server.activity.application.port.in.query;

import com.stumeet.server.activity.domain.model.ActivityCategory;

public record ActivityListDetailedQuery(
	int size,
	int page,
	Boolean isNotice,
	Long memberId,
	Long studyId,
	ActivityCategory category
) {
	public static ActivityListDetailedQuery of(
			int size, int page, Boolean isNotice, Long memberId, Long studyId, String categoryName) {
		ActivityCategory category = categoryName != null ? ActivityCategory.getByName(categoryName) : null;
		return new ActivityListDetailedQuery(size, page, isNotice, memberId, studyId, category);
	}
}
