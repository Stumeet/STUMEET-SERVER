package com.stumeet.server.review.adapter.out.web.dto;

import java.util.List;

import com.stumeet.server.common.model.PageInfoResponse;

import lombok.Builder;

@Builder
public record ReviewDetailResponses(
        List<ReviewDetailResponse> items,
        PageInfoResponse pageInfo
) {
}
