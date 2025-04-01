package com.stumeet.server.activity.adapter.out.persistence;

import java.util.Optional;

import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface JpaActivityRepository extends JpaRepository<ActivityJpaEntity, Long>, JpaActivityRepositoryCustom {

    Optional<ActivityJpaEntity> findByStudyIdAndId(@Param("studyId") Long studyId, @Param("id") Long activityId);

    Optional<ActivityJpaEntity> findByIdAndAuthorId(@Param("id") Long activityId, @Param("authorId") Long memberId);
}
