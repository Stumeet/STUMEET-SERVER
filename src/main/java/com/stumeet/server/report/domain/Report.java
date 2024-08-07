package com.stumeet.server.report.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Report {
    private Long id;
    private ReportCategory category;
    private Long reportedId;
    private Long reporterId;
    private String title;
    private String content;

    @Builder
    private Report(ReportCategory category, Long reportedId, Long reporterId, String title, String content) {
        this.category = category;
        this.reportedId = reportedId;
        this.reporterId = reporterId;
        this.title = title;
        this.content = content;
    }

    public static Report create(ReportCategory category, Long reportedId, Long reporterId, String title, String content) {
        return Report.builder()
                .category(category)
                .reportedId(reportedId)
                .reporterId(reporterId)
                .title(title)
                .content(content)
                .build();
    }
}