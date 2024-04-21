package com.stumeet.server.activity.adapter.out.mapper;

import com.stumeet.server.activity.domain.model.ActivityStatus;
import jakarta.persistence.AttributeConverter;

public class ActivityStatusConverter implements AttributeConverter<ActivityStatus, String> {
    @Override
    public String convertToDatabaseColumn(ActivityStatus activityStatus) {
        return activityStatus.getStatus();
    }

    @Override
    public ActivityStatus convertToEntityAttribute(String s) {
        return ActivityStatus.findByStatus(s);
    }
}
