package com.stumeet.server.review.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.review.application.port.in.ReviewValidationUseCase;
import com.stumeet.server.review.application.port.out.ReviewQueryPort;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ReviewValidationService implements ReviewValidationUseCase {

    private final ReviewQueryPort reviewQueryPort;

    @Override
    public void validateDuplicate(Long studyId, Long reviewerId, Long revieweeId) {
        if (reviewQueryPort.isExists(studyId, reviewerId, revieweeId)) {
            throw new BusinessException(ErrorCode.ALREADY_STUDY_MEMBER_REVIEW_EXISTS);
        }
    }
}
