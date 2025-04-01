package com.stumeet.server.review.adapter.out.persistence.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.stumeet.server.review.domain.ReviewTag;

public record ReviewTagCountDto(
    ReviewTag reviewTag,
    Long count
) {
    @QueryProjection
    public ReviewTagCountDto {
    }
}
