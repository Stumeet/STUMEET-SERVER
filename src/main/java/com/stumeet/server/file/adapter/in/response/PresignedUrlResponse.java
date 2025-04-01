package com.stumeet.server.file.adapter.in.response;

import lombok.Builder;

@Builder
public record PresignedUrlResponse(
        String url
) {
}
