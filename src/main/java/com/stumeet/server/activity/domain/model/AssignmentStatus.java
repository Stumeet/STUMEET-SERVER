package com.stumeet.server.activity.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AssignmentStatus implements ActivityStatus {
    ASSIGNMENT_NOT_STARTED("시작 전"),
    UNSUBMITTED("미제출"),
    PERFORMED("수행"),
    UNPERFORMED("미수행");

    private final String status;

    @Override
    public String getStatus() {
        return this.name();
    }
}
