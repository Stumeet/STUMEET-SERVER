package com.stumeet.server.notification.application.port.in;

public interface SendStudyNoticeUseCase {

    void sendStudyNotice(Long studyId, Long activityId);
}
