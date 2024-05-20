package com.stumeet.server.activity.adapter.out.persistence;

import com.stumeet.server.activity.adapter.out.mapper.ActivityPersistenceMapper;
import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import com.stumeet.server.activity.application.port.out.ActivityCreatePort;
import com.stumeet.server.activity.application.port.out.ActivityQueryPort;
import com.stumeet.server.activity.domain.exception.NotExistsActivityException;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ActivityPersistenceAdapter implements ActivityCreatePort, ActivityQueryPort {

    private final JpaActivityRepository jpaActivityRepository;
    private final ActivityPersistenceMapper activityPersistenceMapper;

    @Override
    public Activity create(Activity activity) {
        ActivityJpaEntity entity = activityPersistenceMapper.toEntity(activity);

        return activityPersistenceMapper.toDomain(jpaActivityRepository.save(entity));
    }

    @Override
    public Activity getById(Long activityId) {
        ActivityJpaEntity activityJpaEntity = jpaActivityRepository.findById(activityId)
                .orElseThrow(() -> new NotExistsActivityException(activityId));

        return activityPersistenceMapper.toDomain(activityJpaEntity);
    }
}
