package com.stumeet.server.activity.application.port.in.query;

import com.stumeet.server.activity.domain.model.ActivityCategory;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ActivityListDetailedQuery {
	private final Integer size;
	private final Integer page;
	private final Boolean isNotice;
	Long memberId;
	Long studyId;
	ActivityCategory category;

	@Builder
	private ActivityListDetailedQuery(Integer size, Integer page, Boolean isNotice, Long memberId, Long studyId, String categoryName) {
		this(size, page, isNotice, memberId, studyId, categoryName != null ? ActivityCategory.getByName(categoryName) : null);
	}

	private ActivityListDetailedQuery(Integer size, Integer page, Boolean isNotice, Long memberId, Long studyId, ActivityCategory category) {
		this.size = size;
		this.page = page;
		this.isNotice = isNotice;
		this.memberId = memberId;
		this.studyId = studyId;
		this.category = category;
	}
}
