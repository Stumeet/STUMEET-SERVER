package com.stumeet.server.activity.adapter.out.persistence;

import com.stumeet.server.activity.adapter.out.model.ActivityParticipantJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaActivityParticipantRepository extends JpaRepository<ActivityParticipantJpaEntity, Long> {
    List<ActivityParticipantJpaEntity> findAllByActivityId(Long activityId);
    void deleteAllByActivityId(Long activityId);
}
