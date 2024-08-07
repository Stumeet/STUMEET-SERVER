package com.stumeet.server.report.application.port.in.command;

import com.stumeet.server.report.domain.ReportCategory;

import lombok.Builder;

public record ReportCreateCommand(
        ReportCategory category,
        Long reportedId,
        Long reporterId,
        String title,
        String content
) {
    @Builder
    public ReportCreateCommand(String category, Long reportedId, Long reporterId, String title, String content) {
        this(ReportCategory.valueOf(category), reportedId, reporterId, title, content);
    }
}
