package com.stumeet.server.activity.adapter.in.response;

import lombok.Builder;

public record ActivityImageResponse(
        Long id,
        String imageUrl
) {
    @Builder
    public ActivityImageResponse {
    }
}
