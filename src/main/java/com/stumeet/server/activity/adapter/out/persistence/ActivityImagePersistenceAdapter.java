package com.stumeet.server.activity.adapter.out.persistence;

import com.stumeet.server.activity.adapter.out.mapper.ActivityImagePersistenceMapper;
import com.stumeet.server.activity.adapter.out.model.ActivityImageJpaEntity;
import com.stumeet.server.activity.application.port.out.ActivityImageCreatePort;
import com.stumeet.server.activity.domain.model.ActivityImage;
import com.stumeet.server.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ActivityImagePersistenceAdapter implements ActivityImageCreatePort {

    private final JpaActivityImageRepository jpaActivityImageRepository;
    private final ActivityImagePersistenceMapper activityImagePersistenceMapper;

    @Override
    public void create(List<ActivityImage> images) {
        List<ActivityImageJpaEntity> entities = activityImagePersistenceMapper.toEntities(images);

        jpaActivityImageRepository.saveAll(entities);
    }
}
