package com.stumeet.server.activity.application.port.in;

public interface ActivityAuthorityValidationUseCase {

    void checkDeleteAuthority(Long studyId, Long memberId, Long activityId);
}
