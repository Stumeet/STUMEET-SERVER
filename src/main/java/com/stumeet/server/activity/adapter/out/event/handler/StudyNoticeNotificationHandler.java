package com.stumeet.server.activity.adapter.out.event.handler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.stumeet.server.activity.adapter.out.event.model.StudyNoticeNotificationEvent;
import com.stumeet.server.notification.application.port.in.StudyNoticeSendUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class StudyNoticeNotificationHandler {

    private final StudyNoticeSendUseCase studyNoticeSendUseCase;

    @Async
    @EventListener(StudyNoticeNotificationEvent.class)
    public void sendNotification(StudyNoticeNotificationEvent event) {
        log.info(event.getMessage());
        studyNoticeSendUseCase.sendStudyNotice(event.getStudyId(), event.getActivityId());
    }
}
