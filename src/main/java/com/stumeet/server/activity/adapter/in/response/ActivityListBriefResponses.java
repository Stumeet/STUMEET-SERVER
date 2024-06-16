package com.stumeet.server.activity.adapter.in.response;

import java.util.List;

import lombok.Builder;

@Builder
public record ActivityListBriefResponses(
	List<ActivityListBriefResponse> items,
	PageInfoResponse pageInfo
) {
}
