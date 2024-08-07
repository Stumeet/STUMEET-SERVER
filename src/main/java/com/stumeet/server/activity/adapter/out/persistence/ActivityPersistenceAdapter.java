package com.stumeet.server.activity.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponse;
import com.stumeet.server.activity.adapter.out.mapper.ActivityPersistenceMapper;
import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import com.stumeet.server.activity.application.port.out.ActivityAuthorValidationPort;
import com.stumeet.server.activity.application.port.out.ActivitySavePort;
import com.stumeet.server.activity.application.port.out.ActivityDeletePort;
import com.stumeet.server.activity.application.port.out.ActivityQueryPort;
import com.stumeet.server.activity.domain.exception.NotExistsActivityException;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.common.annotation.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ActivityPersistenceAdapter implements ActivitySavePort, ActivityQueryPort, ActivityAuthorValidationPort, ActivityDeletePort {

    private final JpaActivityRepository jpaActivityRepository;
    private final ActivityPersistenceMapper activityPersistenceMapper;

    @Override
    public Activity save(Activity activity) {
        ActivityJpaEntity entity = activityPersistenceMapper.toEntity(activity);

        return activityPersistenceMapper.toDomain(jpaActivityRepository.save(entity));
    }

    @Override
    public Activity getById(Long activityId) {
        ActivityJpaEntity entity = jpaActivityRepository.findById(activityId)
                .orElseThrow(() -> new NotExistsActivityException(activityId));

        return activityPersistenceMapper.toDomain(entity);
    }

    @Override
    public Activity getByStudyIdAndId(Long studyId, Long activityId) {
        ActivityJpaEntity entity = jpaActivityRepository.findByStudyIdAndId(studyId, activityId)
                .orElseThrow(() -> new NotExistsActivityException(activityId));

        return activityPersistenceMapper.toDomain(entity);
    }

    @Override
    public Page<Activity> getDetailPagesByCondition(Pageable pageable, Boolean isNotice, Long studyId, ActivityCategory category) {
        Page<ActivityJpaEntity> entities =
                jpaActivityRepository.findDetailPagesByCondition(pageable, isNotice, studyId, category);

        return activityPersistenceMapper.toDomainPages(entities);
    }

    @Override
    public Page<ActivityListBriefResponse> getPaginatedBriefsByCondition(Pageable pageable, Boolean isNotice, Long memberId,
            Long studyId, ActivityCategory category, LocalDateTime startDate, LocalDateTime endDate) {
        return jpaActivityRepository.findBriefsByConditionWithPagination(pageable, isNotice, memberId, studyId, category, startDate, endDate);
    }

    @Override
    public List<ActivityListBriefResponse> getBriefsByCondition(Boolean isNotice, Long memberId, Long studyId,
            ActivityCategory category, LocalDateTime startDate, LocalDateTime endDate) {
        return jpaActivityRepository.findBriefsByCondition(isNotice, memberId, studyId, category, startDate, endDate);
    }

    @Override
    public boolean isNotActivityAuthor(Long memberId, Long activityId) {
        return jpaActivityRepository.findByIdAndAuthorId(activityId, memberId)
                .isEmpty();
    }

    @Override
    public void deleteById(Long activityId) {
        jpaActivityRepository.deleteById(activityId);
    }
}
