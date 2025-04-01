package com.stumeet.server.report.application.port.out.dto;

import lombok.Builder;

@Builder
public record ReportRequest(
        String content,
        String username
) {
}
