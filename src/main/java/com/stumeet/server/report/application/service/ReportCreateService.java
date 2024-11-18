package com.stumeet.server.report.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import com.stumeet.server.report.application.port.in.ReportCreateUseCase;
import com.stumeet.server.report.application.port.in.command.ReportCreateCommand;
import com.stumeet.server.report.application.port.out.ReportCommandPort;
import com.stumeet.server.report.domain.Report;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ReportCreateService implements ReportCreateUseCase {

    private final MemberValidationUseCase memberValidationUseCase;

    private final ReportCommandPort reportCommandPort;

    @Override
    public Long report(ReportCreateCommand command) {
        memberValidationUseCase.checkById(command.reporterId());

        Report report = Report.create(
                command.category(),
                command.reason(),
                command.reportedId(),
                command.reporterId(),
                command.content()
        );

        return reportCommandPort.save(report);
    }
}
