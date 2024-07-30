package com.stumeet.server.file.adapter.in.response;

import java.util.List;

public record PresignedUrlResponses(
        List<PresignedUrlResponse> presignedUrls
) {
}
