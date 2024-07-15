package com.stumeet.server.activity.domain.model;

import java.time.LocalDateTime;

import com.stumeet.server.common.exception.model.BadRequestException;
import com.stumeet.server.common.response.ErrorCode;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ActivityPeriod {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Builder
    private ActivityPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        validate(startDate, endDate);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validate(LocalDateTime startDate, LocalDateTime endDate) {
        validateNonNull(startDate, endDate);
        validatePeriod(startDate, endDate);
    }

    private void validateNonNull(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            throw new BadRequestException(ErrorCode.ACTIVITY_PERIOD_REQUIRED_EXCEPTION);
        }
    }

    private void validatePeriod(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BadRequestException(ErrorCode.INVALID_PERIOD_EXCEPTION);
        }
    }
}
