package com.stumeet.server.activity.application.port.in.query;

import java.util.List;

import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.activity.domain.model.ActivitySort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ActivityListDetailedQuery {
	private final Integer size;
	private final Integer page;
	private final Boolean isNotice;
	Long memberId;
	Long studyId;
	List<ActivityCategory> categories;
	ActivitySort sort;
}
