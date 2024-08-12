package com.stumeet.server.report.domain.exception;

import java.text.MessageFormat;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

public class NotExistsReportReasonException extends NotExistsException {
    private static final String MESSAGE = "존재하지 않는 신고 사유입니다. 입력받은 신고 사유 : {0}";

    public NotExistsReportReasonException(String description) {
        super(MessageFormat.format(MESSAGE, description), ErrorCode.REPORT_REASON_NOT_FOUND);
    }
}
