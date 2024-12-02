package com.stumeet.server.review.application.port.in;

public interface ReviewValidationUseCase {

    public void validateDuplicate(Long studyId, Long reviewerId, Long revieweeId);
}