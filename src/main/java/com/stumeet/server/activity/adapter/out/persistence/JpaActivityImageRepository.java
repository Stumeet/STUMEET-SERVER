package com.stumeet.server.activity.adapter.out.persistence;

import com.stumeet.server.activity.adapter.out.model.ActivityImageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaActivityImageRepository extends JpaRepository<ActivityImageJpaEntity, Long> {
}
