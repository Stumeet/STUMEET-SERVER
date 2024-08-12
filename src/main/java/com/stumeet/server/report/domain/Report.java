package com.stumeet.server.report.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Report {
    private Long id;
    private ReportCategory category;
    private ReportReason reason;
    private Long reportedId;
    private Long reporterId;
    private String content;
    private LocalDateTime createdAt;

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