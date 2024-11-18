package com.stumeet.server.stub;

import com.stumeet.server.report.adapter.in.request.ReportCreateRequest;
import com.stumeet.server.report.domain.ReportCategory;

public class ReportStub {

    public static ReportCreateRequest getStudyReportCreateRequest() {
        return new ReportCreateRequest(
            ReportCategory.STUDY.name(),
            "COMMERCIAL_ADVERTISEMENT_AND_SPAM",
            1L,
            1L,
            "상업적 광고 스터디입니다."
        );
    }

    public static ReportCreateRequest getActivityReportCreateRequest() {
        return new ReportCreateRequest(
            ReportCategory.ACTIVITY.name(),
            "COMMERCIAL_ADVERTISEMENT_AND_SPAM",
            1L,
            1L,
            "상업적 광고 활동입니다."
        );
    }

    public static ReportCreateRequest getNotFoundReporterIdReportCreateRequest() {
        return new ReportCreateRequest(
            ReportCategory.ACTIVITY.name(),
            "COMMERCIAL_ADVERTISEMENT_AND_SPAM",
            1L,
            0L,
            "상업적 광고 활동입니다."
        );
    }
}
