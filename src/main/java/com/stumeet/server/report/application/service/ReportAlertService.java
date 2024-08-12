package com.stumeet.server.report.application.service;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.activity.application.port.out.ActivityQueryPort;
import com.stumeet.server.member.application.port.out.MemberQueryPort;
import com.stumeet.server.member.domain.Member;
import com.stumeet.server.report.application.port.in.ReportAlertUseCase;
import com.stumeet.server.report.application.port.out.ReportAlertPort;
import com.stumeet.server.report.application.port.out.ReportQueryPort;
import com.stumeet.server.report.application.port.out.dto.ReportRequest;
import com.stumeet.server.report.domain.Report;
import com.stumeet.server.study.application.port.out.StudyQueryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportAlertService implements ReportAlertUseCase {

    private final ReportAlertPort reportAlertPort;

    private final ReportQueryPort reportQueryPort;
    private final MemberQueryPort memberQueryPort;
    private final ActivityQueryPort activityQueryPort;
    private final StudyQueryPort studyQueryPort;

    @Override
    public void alert(Long reportId) {
        Report report = reportQueryPort.findById(reportId);
        Member reporter = memberQueryPort.getById(report.getReportedId());

        ReportRequest request = ReportRequest.builder()
                .content(makeContent(report, reporter))
                .username(reporter.getName())
                .build();

        reportAlertPort.report(request);
    }

    private String makeContent(Report report, Member reporter) {
        StringBuilder content = new StringBuilder();

        String originalText = switch (report.getCategory()) {
            case STUDY -> studyQueryPort.getById(report.getReportedId()).toString();
            case ACTIVITY -> activityQueryPort.getById(report.getReportedId()).toString();
        };

        content.append("## ").append(report.getCreatedAt().format(DateTimeFormatter.ISO_DATE)).append("\n");
        content.append("- 카테고리: ").append(report.getCategory().name()).append("\n");
        content.append("- 신고자 ID: ").append(reporter.getId()).append("\n");
        content.append("- 신고자명: ").append(reporter.getName()).append("\n");
        content.append("- 사유: ").append(report.getReason().getDescription()).append("\n");
        content.append("- 내용: ").append(report.getContent()).append("\n");
        content.append("### 원문").append("\n");
        content.append(originalText);

        return content.toString();
    }
}
