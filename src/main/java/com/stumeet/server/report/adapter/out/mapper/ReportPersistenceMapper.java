package com.stumeet.server.report.adapter.out.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.report.adapter.out.model.ReportJpaEntity;
import com.stumeet.server.report.domain.Report;

@Component
public class ReportPersistenceMapper {

    public ReportJpaEntity toEntity(Report report) {
        return ReportJpaEntity.builder()
                .id(report.getId())
                .category(report.getCategory())
                .reason(report.getReason())
                .reportedId(report.getReportedId())
                .reporterId(report.getReporterId())
                .content(report.getContent())
                .build();
    }
}
