package com.stumeet.server.activity.domain.model;

import java.util.Arrays;

import com.stumeet.server.activity.domain.exception.NotExistsActivitySortException;

public enum ActivitySort {
    LATEST,
    SPECIFIC;

    public static ActivitySort getByName(String sort) {
        return Arrays.stream(ActivitySort.values())
                .filter(s -> s.name().equalsIgnoreCase(sort))
                .findAny()
                .orElseThrow(() -> new NotExistsActivitySortException(sort));
    }
}