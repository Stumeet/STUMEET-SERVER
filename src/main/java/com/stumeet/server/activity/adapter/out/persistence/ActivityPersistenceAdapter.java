package com.stumeet.server.activity.adapter.out.persistence;

import com.stumeet.server.activity.adapter.out.mapper.ActivityPersistenceMapper;
import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import com.stumeet.server.activity.application.port.out.ActivityCreatePort;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ActivityPersistenceAdapter implements ActivityCreatePort {

    private final JpaActivityRepository jpaActivityRepository;
    private final ActivityPersistenceMapper activityPersistenceMapper;

    @Override
    public Activity create(Activity activity) {
        ActivityJpaEntity entity = activityPersistenceMapper.toEntity(activity);

        return activityPersistenceMapper.toDomain(jpaActivityRepository.save(entity));
    }
}
