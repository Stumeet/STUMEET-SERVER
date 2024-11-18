package com.stumeet.server.activity.adapter.out.persistence;

import com.stumeet.server.activity.adapter.out.model.ActivityParticipantJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaActivityParticipantRepository extends JpaRepository<ActivityParticipantJpaEntity, Long> {

    Optional<ActivityParticipantJpaEntity> findByActivityIdAndMemberIdAndId(Long activityId, Long memberId, Long id);

    List<ActivityParticipantJpaEntity> findAllByActivityId(Long activityId);

    List<ActivityParticipantJpaEntity> findAllByActivityStudyIdAndMemberId(Long studyId, Long memberId);

    void deleteAllByActivityId(Long activityId);
}
