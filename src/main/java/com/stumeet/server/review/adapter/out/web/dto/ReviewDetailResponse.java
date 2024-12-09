package com.stumeet.server.review.adapter.out.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

public record ReviewDetailResponse(
    Long id,
    Integer rate,
    String content,
    LocalDateTime createdAt,
    List<String> tags
) {
    @QueryProjection
    public ReviewDetailResponse {
    }
}
