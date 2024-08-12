package com.stumeet.server.report.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Report {
    private Long id;
    private ReportCategory category;
    private ReportReason reason;
    private Long reportedId;
    private Long reporterId;
    private String content;

    @Builder
    private Report(ReportCategory category, ReportReason reason, Long reportedId, Long reporterId, String content) {
        this.category = category;
        this.reason = reason;
        this.reportedId = reportedId;
        this.reporterId = reporterId;
        this.content = content;
    }

    public static Report create(ReportCategory category, ReportReason reason, Long reportedId, Long reporterId, String content) {
        return Report.builder()
                .category(category)
                .reason(reason)
                .reportedId(reportedId)
                .reporterId(reporterId)
                .content(content)
                .build();
    }
}