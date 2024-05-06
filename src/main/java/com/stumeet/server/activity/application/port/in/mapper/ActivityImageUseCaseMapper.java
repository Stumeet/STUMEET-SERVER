package com.stumeet.server.activity.application.port.in.mapper;

import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityImage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityImageUseCaseMapper {

    public ActivityImage toDomain(String image, Activity activity) {
        return ActivityImage.builder()
                .activity(activity)
                .url(image)
                .build();

    }
    public List<ActivityImage> toDomains(List<String> images, Activity activity) {
        return images.stream()
                .map(image -> toDomain(image, activity))
                .toList();
    }
}
