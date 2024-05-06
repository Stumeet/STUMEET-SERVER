package com.stumeet.server.file.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FileManagementPath {
    USER_PROFILE("user/%d/profile"),
    STUDY("study/%d/main"),
    STUDY_ACTIVITY("study/%d/activity")
    ;

    private final String path;
}
