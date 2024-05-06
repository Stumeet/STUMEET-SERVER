package com.stumeet.server.activity.adapter.out.persistence;

import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaActivityRepository extends JpaRepository<ActivityJpaEntity, Long> {
}
