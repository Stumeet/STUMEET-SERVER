package com.stumeet.server.activity.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DefaultStatus implements ActivityStatus {
    NONE;

    @Override
    public String getStatus() {
        return this.name();
    }
}
