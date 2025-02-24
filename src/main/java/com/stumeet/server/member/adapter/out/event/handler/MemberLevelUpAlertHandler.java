package com.stumeet.server.member.adapter.out.event.handler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.stumeet.server.member.adapter.out.event.model.MemberLevelUpAlertEvent;
import com.stumeet.server.notification.application.port.in.SendUserLevelUpAlertUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class MemberLevelUpAlertHandler {

    private final SendUserLevelUpAlertUseCase sendUserLevelUpAlertUseCase;

    @Async
    @EventListener(MemberLevelUpAlertEvent.class)
    public void sendNotification(MemberLevelUpAlertEvent event) {
        log.info(event.getMessage());
        sendUserLevelUpAlertUseCase.sendUserLevelUpAlert(event.getMemberId(), event.getTierName());
    }
}
