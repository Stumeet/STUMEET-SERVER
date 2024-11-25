package com.stumeet.server.activity.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AssignmentStatus implements ActivityStatus {
    ASSIGNMENT_NOT_STARTED("시작 전"),
    PERFORMED("수행"),
    LATE_SUBMISSION("지각제출"),
    UNPERFORMED("미수행");

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
        return this.equals(PERFORMED);
    }
}
