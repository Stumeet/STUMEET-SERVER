package com.stumeet.server.activity.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.stumeet.server.activity.domain.exception.NotExistsActivityStatusException;

import java.util.stream.Stream;

public interface ActivityStatus {
    String getStatus();

    @JsonValue
    String getDescription();

    static ActivityStatus findByStatus(String status) {
        return Stream.of(MeetStatus.values(), DefaultStatus.values(), AssignmentStatus.values())
                .flatMap(Stream::of)
                .filter(activityStatus -> activityStatus.getStatus().equals(status))
                .findAny()
                .orElseThrow(() -> new NotExistsActivityStatusException(status));
    }
}
