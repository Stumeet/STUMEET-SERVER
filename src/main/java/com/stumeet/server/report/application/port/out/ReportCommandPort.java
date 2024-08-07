package com.stumeet.server.report.application.port.out;

import com.stumeet.server.report.domain.Report;

public interface ReportCommandPort {

    Long save(Report report);
}
