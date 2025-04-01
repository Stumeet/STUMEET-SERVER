package com.stumeet.server.activity.application.port.in.query;

import java.time.LocalDateTime;
import java.util.List;

import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.activity.domain.model.ActivitySort;
import com.stumeet.server.common.exception.model.BadRequestException;
import com.stumeet.server.common.response.ErrorCode;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ActivityListBriefQuery {
	private final Integer size;
	private final Integer page;
	private final Boolean isNotice;
	private final Long memberId;
	private final Long studyId;
	private final LocalDateTime fromDate;
	private final LocalDateTime toDate;
	private List<ActivityCategory> categories;
	private ActivitySort sort;

	@Builder
	private ActivityListBriefQuery(Integer size, Integer page, Boolean isNotice, Long memberId, Long studyId,
			LocalDateTime fromDate, LocalDateTime toDate, List<ActivityCategory> categories, ActivitySort sort) {
		this.size = size;
		this.page = page;
		this.isNotice = isNotice;
		this.memberId = memberId;
		this.studyId = studyId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.categories = categories;
		this.sort = sort;
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
