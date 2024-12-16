package com.stumeet.server.review.adapter.out.web.dto;

import java.util.Map;

import com.stumeet.server.review.domain.ReviewTag;

public record ReviewTagStatsResponse(
    Map<ReviewTag, Long> tagCounts
) {
}