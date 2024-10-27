package com.stumeet.server.notification.application.port.in;

public interface StudyNoticeSendUseCase {

    void sendStudyNotice(Long studyId, Long activityId);
}
