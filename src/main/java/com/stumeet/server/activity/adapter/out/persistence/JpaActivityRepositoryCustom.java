package com.stumeet.server.activity.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponse;
import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import com.stumeet.server.activity.domain.model.ActivityCategory;

public interface JpaActivityRepositoryCustom {
	Page<ActivityJpaEntity> findDetailPagesByCondition(Pageable pageable, Boolean isNotice, Long studyId, ActivityCategory category);

	Page<ActivityListBriefResponse> findBriefsByConditionWithPagination(Pageable pageable, Boolean isNotice, Long memberId, Long studyId, ActivityCategory category, LocalDateTime startDate, LocalDateTime endDate);

	List<ActivityListBriefResponse> findBriefsByCondition(Boolean isNotice, Long memberId, Long studyId, ActivityCategory category, LocalDateTime startDate, LocalDateTime endDate);
}
