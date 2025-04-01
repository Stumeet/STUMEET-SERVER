package com.stumeet.server.report.adapter.in.request;

public record ReportCreateRequest(
        String category,
        String reason,
        Long reportedId,
        Long reporterId,
        String content
) {
}
