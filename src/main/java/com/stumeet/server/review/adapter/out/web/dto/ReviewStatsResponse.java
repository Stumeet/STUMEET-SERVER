package com.stumeet.server.review.adapter.out.web.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record ReviewStatsResponse(
        long totalCount,
        List<ReviewTagCountStatsResponse> tagCountStats
) {
}
