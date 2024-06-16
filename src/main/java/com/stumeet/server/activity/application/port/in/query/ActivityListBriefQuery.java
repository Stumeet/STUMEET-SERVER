package com.stumeet.server.activity.application.port.in.query;

import java.time.LocalDateTime;

import com.stumeet.server.activity.application.exception.InvalidPaginationParametersException;
import com.stumeet.server.activity.domain.model.ActivityCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ActivityListBriefQuery {
	private final Integer size;
	private final Integer page;
	private final Boolean isNotice;
	private final Long memberId;
	private final Long studyId;
	private final ActivityCategory category;
	private final LocalDateTime startDate;
	private final LocalDateTime endDate;

	@Builder
	private ActivityListBriefQuery(Integer size, Integer page, Boolean isNotice, Long memberId, Long studyId,
			String categoryName, LocalDateTime startDate, LocalDateTime endDate) {
		this(size, page, isNotice, memberId, studyId,
				categoryName != null ? ActivityCategory.getByName(categoryName) : null,
				startDate, endDate);
		validate(size, page);
	}

	private void validate(Integer size, Integer page) {
		if (isIncompletePaginationRequest(size, page)) {
			throw new InvalidPaginationParametersException();
		}
	}

	private boolean isIncompletePaginationRequest(Integer size, Integer page) {
		return ((size != null) && (page == null)) || ((size == null) && (page != null));
	}

	public boolean isPaginationRequest() {
		return (this.size != null) && (this.page != null);
	}
}
