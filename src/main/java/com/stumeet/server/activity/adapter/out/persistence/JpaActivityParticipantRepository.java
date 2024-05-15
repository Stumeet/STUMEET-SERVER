package com.stumeet.server.activity.adapter.out.persistence;

import com.stumeet.server.activity.adapter.out.model.ActivityParticipantJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaActivityParticipantRepository extends JpaRepository<ActivityParticipantJpaEntity, Long> {
}
