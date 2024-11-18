package com.stumeet.server.report.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.report.adapter.out.model.ReportJpaEntity;

public interface JpaReportRepository extends JpaRepository<ReportJpaEntity, Long> {
}
