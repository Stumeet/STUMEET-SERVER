package com.stumeet.server.activity.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MeetStatus implements ActivityStatus {
    MEET_NOT_STARTED("시작전"),
    ATTENDANCE("출석"),
    ABSENCE("결석"),
    ACKNOWLEDGED_ABSENCE("인정 결석"),
    TARDINESS("지각")
    ;

    private final String status;

    @Override
    public String getStatus() {
        return this.name();
    }
}
