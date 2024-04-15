package com.stumeet.server.activity.adapter.out.mapper;

import com.stumeet.server.activity.domain.exception.NotExistsActivityStatusException;
import com.stumeet.server.activity.domain.model.ActivityStatus;
import com.stumeet.server.activity.domain.model.AssignmentStatus;
import com.stumeet.server.activity.domain.model.DefaultStatus;
import com.stumeet.server.activity.domain.model.MeetStatus;
import jakarta.persistence.AttributeConverter;

import java.util.stream.Stream;

public class ActivityStatusConverter implements AttributeConverter<ActivityStatus, String> {
    @Override
    public String convertToDatabaseColumn(ActivityStatus activityStatus) {
        return activityStatus.getStatus();
    }

    @Override
    public ActivityStatus convertToEntityAttribute(String s) {
        return Stream.of(MeetStatus.values(), DefaultStatus.values(), AssignmentStatus.values())
                .flatMap(Stream::of)
                .filter(activityStatus -> activityStatus.getStatus().equals(s))
                .findAny()
                .orElseThrow(() -> new NotExistsActivityStatusException(s));
    }
}
