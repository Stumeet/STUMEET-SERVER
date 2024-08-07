package com.stumeet.server.report.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.report.application.port.in.ReportCreateUseCase;
import com.stumeet.server.report.application.port.in.command.ReportCreateCommand;
import com.stumeet.server.report.application.port.out.ReportCommandPort;
import com.stumeet.server.report.domain.Report;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ReportCreateService implements ReportCreateUseCase {

    private final ReportCommandPort reportCommandPort;

    @Override
    public Long report(ReportCreateCommand command) {
        Report report =
                Report.create(command.category(), command.reportedId(), command.reporterId(), command.title(), command.content());

        return reportCommandPort.save(report);
    }
}
