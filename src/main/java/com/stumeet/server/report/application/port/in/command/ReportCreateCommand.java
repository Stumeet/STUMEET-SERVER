package com.stumeet.server.report.application.port.in.command;

import com.stumeet.server.report.domain.ReportCategory;
import com.stumeet.server.report.domain.ReportReason;

import lombok.Builder;

public record ReportCreateCommand(
        ReportCategory category,
        ReportReason reason,
        Long reportedId,
        Long reporterId,
        String title,
        String content
) {
    @Builder
    public ReportCreateCommand(String category, String reason, Long reportedId, Long reporterId, String title, String content) {
        this(ReportCategory.find(category), ReportReason.find(reason), reportedId, reporterId, title, content);
    }
}
