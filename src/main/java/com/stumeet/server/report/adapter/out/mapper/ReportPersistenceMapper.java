package com.stumeet.server.report.adapter.out.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.report.adapter.out.model.ReportJpaEntity;
import com.stumeet.server.report.domain.Report;

@Component
public class ReportPersistenceMapper {

    public ReportJpaEntity toEntity(Report domain) {
        return ReportJpaEntity.builder()
                .id(domain.getId())
                .category(domain.getCategory())
                .reason(domain.getReason())
                .reportedId(domain.getReportedId())
                .reporterId(domain.getReporterId())
                .content(domain.getContent())
                .build();
    }

    public Report toDomain(ReportJpaEntity entity) {
        return Report.builder()
                .id(entity.getId())
                .category(entity.getCategory())
                .reason(entity.getReason())
                .reportedId(entity.getReportedId())
                .reporterId(entity.getReporterId())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
