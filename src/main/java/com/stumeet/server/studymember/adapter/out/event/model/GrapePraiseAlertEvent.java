package com.stumeet.server.studymember.adapter.out.event.model;

import com.stumeet.server.common.event.model.Event;

import lombok.Getter;

@Getter
public class GrapePraiseAlertEvent extends Event {

    private final Long studyId;
    private final Long memberId;

    public GrapePraiseAlertEvent(Object source, Long studyId, Long memberId) {
        super(source, String.format("[포도알 칭찬 알림 발송] studyId: %d, memberId: %d", studyId, memberId));
        this.studyId = studyId;
        this.memberId = memberId;
    }
}
