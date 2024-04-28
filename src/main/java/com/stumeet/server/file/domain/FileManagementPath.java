package com.stumeet.server.file.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FileManagementPath {
    ACTIVITY("activity");

    private final String path;
}
