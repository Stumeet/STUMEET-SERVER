package com.stumeet.server.report.domain;

import com.stumeet.server.report.domain.exception.NotExistsReportReasonException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReportReason {

    COMMERCIAL_ADVERTISEMENT_AND_SPAM("상업적 광고 및 스팸"),
    PORNOGRAPHY_AND_HARMFUL_ACTIVITIES("음란물 및 유해성 활동"),
    PROFANITY_AND_SLANDER("욕설 및 비방"),
    FRAUD_OR_IMPERSONATION("사기 또는 사칭"),
    FLOODING("도배"),
    OTHERS("기타")
    ;

    private final String description;

    public static ReportReason find(String name) {
        try {
            return ReportReason.valueOf(name);
        } catch(IllegalArgumentException e) {
            throw new NotExistsReportReasonException(name);
        }
    }
}
