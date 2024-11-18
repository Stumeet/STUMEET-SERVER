package com.stumeet.server.report.application.service;

import org.springframework.stereotype.Service;

import com.stumeet.server.report.adapter.out.mapper.ReportPersistenceMapper;
import com.stumeet.server.report.adapter.out.model.ReportJpaEntity;
import com.stumeet.server.report.adapter.out.persistence.JpaReportRepository;
import com.stumeet.server.report.application.port.out.ReportQueryPort;
import com.stumeet.server.report.domain.Report;
import com.stumeet.server.report.domain.exception.NotExistsReportException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportQueryService implements ReportQueryPort {

    private final JpaReportRepository jpaReportRepository;
    private final ReportPersistenceMapper reportPersistenceMapper;

    @Override
    public Report findById(Long id) {
        ReportJpaEntity entity = jpaReportRepository.findById(id)
                .orElseThrow(
                        () -> new NotExistsReportException(id));

        return reportPersistenceMapper.toDomain(entity);
    }
}
