package com.stumeet.server.report.domain;

import com.stumeet.server.report.domain.exception.NotExistsReportReasonException;

import lombok.Getter;

@Getter
public enum ReportCategory {

    STUDY,
    ACTIVITY
    ;

    public static ReportCategory find(String name) {
        try {
            return ReportCategory.valueOf(name);
        } catch(IllegalArgumentException e) {
            throw new NotExistsReportReasonException(name);
        }
    }
}