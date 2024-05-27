package com.stumeet.server.activity.adapter.out.persistence;

import com.stumeet.server.activity.adapter.out.model.ActivityImageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaActivityImageRepository extends JpaRepository<ActivityImageJpaEntity, Long> {
    List<ActivityImageJpaEntity> findAllByActivityId(Long activityId);
}
