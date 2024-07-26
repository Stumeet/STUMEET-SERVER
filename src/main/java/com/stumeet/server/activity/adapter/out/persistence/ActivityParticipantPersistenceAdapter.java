package com.stumeet.server.activity.adapter.out.persistence;

import com.stumeet.server.activity.adapter.out.mapper.ActivityParticipantPersistenceMapper;
import com.stumeet.server.activity.adapter.out.model.ActivityParticipantJpaEntity;
import com.stumeet.server.activity.application.port.out.ActivityParticipantCommandPort;
import com.stumeet.server.activity.application.port.out.ActivityParticipantCreatePort;
import com.stumeet.server.activity.application.port.out.ActivityParticipantQueryPort;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import com.stumeet.server.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ActivityParticipantPersistenceAdapter implements ActivityParticipantCreatePort, ActivityParticipantQueryPort, ActivityParticipantCommandPort {

    private final JpaActivityParticipantRepository jpaActivityParticipantRepository;
    private final ActivityParticipantPersistenceMapper activityParticipantPersistenceMapper;


    @Override
    public void create(List<ActivityParticipant> participants) {
        List<ActivityParticipantJpaEntity> entities = activityParticipantPersistenceMapper.toEntities(participants);

        jpaActivityParticipantRepository.saveAll(entities);
    }

    @Override
    public List<ActivityParticipant> findAllByActivityId(Long activityId) {
        return jpaActivityParticipantRepository.findAllByActivityId(activityId).stream()
                .map(activityParticipantPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteByActivityId(Long activityId) {
        jpaActivityParticipantRepository.deleteAllByActivityId(activityId);
    }

    @Override
    public void update(Long activityId, List<ActivityParticipant> participants) {
        deleteByActivityId(activityId);
        create(participants);
    }
}
