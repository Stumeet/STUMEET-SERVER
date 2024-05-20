package com.stumeet.server.file.application.port.in.response;

import lombok.Builder;

@Builder
public record PresignedUrlResponse(
        String url
) {
}
