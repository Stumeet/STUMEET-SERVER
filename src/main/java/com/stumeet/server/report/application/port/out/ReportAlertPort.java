package com.stumeet.server.report.application.port.out;

import com.stumeet.server.report.application.port.out.dto.ReportRequest;

public interface ReportAlertPort {

    void report(ReportRequest request);
}
