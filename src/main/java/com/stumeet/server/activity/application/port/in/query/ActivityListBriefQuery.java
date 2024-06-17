package com.stumeet.server.activity.application.port.in.query;

import java.time.LocalDateTime;

import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.common.exception.model.BadRequestException;
import com.stumeet.server.common.response.ErrorCode;

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
	private final LocalDateTime fromDate;
	private final LocalDateTime toDate;

	@Builder
	private ActivityListBriefQuery(Integer size, Integer page, Boolean isNotice, Long memberId, Long studyId,
			String categoryName, LocalDateTime fromDate, LocalDateTime toDate) {
		this(size, page, isNotice, memberId, studyId,
				categoryName != null ? ActivityCategory.getByName(categoryName) : null,
				fromDate, toDate);
		validate();
	}

	private void validate() {
		if (isIncompletePaginationRequest()) {
			throw new BadRequestException(ErrorCode.INVALID_PAGINATION_PARAMETERS_EXCEPTION);
		}

		if (isFromDateAfterToDate()) {
			throw new BadRequestException(ErrorCode.INVALID_PERIOD_EXCEPTION);
		}
	}

	private boolean isIncompletePaginationRequest() {
		return ((this.size != null) && (this.page == null)) || ((this.size == null) && (this.page != null));
	}

	private boolean isFromDateAfterToDate() {
		return (fromDate != null && toDate != null) && (this.fromDate.isAfter(this.toDate));
	}

	public boolean isPaginationRequest() {
		return (this.size != null) && (this.page != null);
	}
}
