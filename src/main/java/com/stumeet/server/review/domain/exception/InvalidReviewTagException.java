package com.stumeet.server.review.domain.exception;

import com.stumeet.server.common.exception.model.BadRequestException;
import com.stumeet.server.common.response.ErrorCode;

public class InvalidReviewTagException extends BadRequestException {
    public InvalidReviewTagException() {
        super(ErrorCode.INVALID_REVIEW_TAG_COUNT_EXCEPTION);
    }
}
