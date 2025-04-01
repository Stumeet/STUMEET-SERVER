package com.stumeet.server.activity.domain.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CommonStatus implements ActivityStatus {
    NOT_JOINED("미참여");

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
        return false;
    }
}
