package com.stumeet.server.report.application.port.in;

import com.stumeet.server.report.application.port.in.command.ReportCreateCommand;

public interface ReportCreateUseCase {

    Long report(ReportCreateCommand command);
}
