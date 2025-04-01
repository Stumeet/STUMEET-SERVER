package com.stumeet.server.report.application.port.out;

import com.stumeet.server.report.domain.Report;

public interface ReportQueryPort {

    Report findById(Long id);
}
