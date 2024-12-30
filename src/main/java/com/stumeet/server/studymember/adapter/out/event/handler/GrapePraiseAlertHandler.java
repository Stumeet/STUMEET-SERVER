package com.stumeet.server.studymember.adapter.out.event.handler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.stumeet.server.notification.application.port.in.SendGrapePraiseAlertUseCase;
import com.stumeet.server.studymember.adapter.out.event.model.GrapePraiseAlertEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class GrapePraiseAlertHandler {

    private final SendGrapePraiseAlertUseCase sendGrapePraiseAlertUseCase;

    @Async
    @EventListener(GrapePraiseAlertEvent.class)
    public void deleteActivityImage(GrapePraiseAlertEvent event) {
        log.info(event.getMessage());
        sendGrapePraiseAlertUseCase.sendGrapeAlert(event.getStudyId(), event.getMemberId());
    }
}
