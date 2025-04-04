package com.stumeet.server.activity.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MeetStatus implements ActivityStatus {
    MEET_NOT_STARTED("시작 전"),
    ATTENDANCE("출석"),
    TARDINESS("지각"),
    ACKNOWLEDGED_ABSENCE("인정결석"),
    ABSENCE("결석")
    ;

    private final String description;

    @Override
    public String getStatus() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isSuccessfulStatus() {
        return this.equals(ATTENDANCE) || this.equals(ACKNOWLEDGED_ABSENCE);
    }
}
