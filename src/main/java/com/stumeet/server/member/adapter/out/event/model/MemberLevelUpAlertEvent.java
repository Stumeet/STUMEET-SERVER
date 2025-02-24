package com.stumeet.server.member.adapter.out.event.model;

import com.stumeet.server.common.event.model.Event;

import lombok.Getter;

@Getter
public class MemberLevelUpAlertEvent extends Event {
    private final long memberId;
    private final String tierName;

    public MemberLevelUpAlertEvent(Object source, long memberId, String tierName) {
        super(source, String.format("[유저 레벨 업] memberId: %d", memberId));

        this.memberId = memberId;
        this.tierName = tierName;
    }
}
