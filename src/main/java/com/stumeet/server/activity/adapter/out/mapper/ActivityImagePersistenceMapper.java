package com.stumeet.server.activity.adapter.out.mapper;

import com.stumeet.server.activity.adapter.out.model.ActivityImageJpaEntity;
import com.stumeet.server.activity.domain.model.ActivityImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ActivityImagePersistenceMapper {

    private final ActivityPersistenceMapper activityPersistenceMapper;

    public ActivityImage toDomain(ActivityImageJpaEntity entity) {
        return ActivityImage.builder()
                .id(entity.getId())
                .activity(activityPersistenceMapper.toDomain(entity.getActivity()))
                .url(entity.getUrl())
                .build();
    }

    public ActivityImageJpaEntity toEntity(ActivityImage domain) {
        return ActivityImageJpaEntity.builder()
                .id(domain.getId())
                .activity(activityPersistenceMapper.toEntity(domain.getActivity()))
                .url(domain.getUrl())
                .build();
    }

    public List<ActivityImageJpaEntity> toEntities(List<ActivityImage> images) {
        return images.stream()
                .map(this::toEntity)
                .toList();
    }
}
