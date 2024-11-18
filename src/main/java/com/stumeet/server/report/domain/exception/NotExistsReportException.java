package com.stumeet.server.report.domain.exception;

import java.text.MessageFormat;

import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;

public class NotExistsReportException extends BusinessException {
    private static final String MESSAGE = "존재하지 않는 신고입니다. 입력받은 신고 : {0}";

    public NotExistsReportException(Long id) {
        super(MessageFormat.format(MESSAGE, id), ErrorCode.REPORT_NOT_FOUND);
    }
}
