package com.stumeet.server.review.application.port.out;

public interface ReviewQueryPort {

    public boolean isExists(Long studyId, Long reviewerId, Long revieweeId);
}
