package com.stumeet.server.report.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.report.adapter.out.mapper.ReportPersistenceMapper;
import com.stumeet.server.report.adapter.out.model.ReportJpaEntity;
import com.stumeet.server.report.application.port.out.ReportCommandPort;
import com.stumeet.server.report.domain.Report;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReportPersistenceAdapter implements ReportCommandPort {

    private final JpaReportRepository jpaReportRepository;

    private final ReportPersistenceMapper reportPersistenceMapper;

    @Override
    public Long save(Report report) {
        ReportJpaEntity entity = reportPersistenceMapper.toEntity(report);

        return jpaReportRepository.save(entity).getId();
    }
}
