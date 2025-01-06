package com.stumeet.server.review.adapter.out.web.dto;

import lombok.Builder;

@Builder
public record ReviewTagCountStatsResponse(
    String reviewTagName,
    long count
) {
}