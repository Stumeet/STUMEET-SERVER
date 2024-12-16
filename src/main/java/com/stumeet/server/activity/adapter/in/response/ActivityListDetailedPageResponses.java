package com.stumeet.server.activity.adapter.in.response;

import java.util.List;

import com.stumeet.server.common.model.PageInfoResponse;

import lombok.Builder;

@Builder
public record ActivityListDetailedPageResponses(
        List<ActivityListDetailedPageResponse> items,
        PageInfoResponse pageInfo
) {
}
