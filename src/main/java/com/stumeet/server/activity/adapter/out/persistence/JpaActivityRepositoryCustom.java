package com.stumeet.server.activity.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponse;
import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.activity.domain.model.ActivitySort;

public interface JpaActivityRepositoryCustom {
	Page<ActivityJpaEntity> findDetailPagesByCondition(Pageable pageable, Boolean isNotice, Long studyId, List<ActivityCategory> categories, ActivitySort sort);

	Page<ActivityListBriefResponse> findBriefsByConditionWithPagination(Pageable pageable, Boolean isNotice, Long memberId, Long studyId, List<ActivityCategory> categories, LocalDateTime startDate, LocalDateTime endDate, ActivitySort sort);

	List<ActivityListBriefResponse> findBriefsByCondition(Boolean isNotice, Long memberId, Long studyId, List<ActivityCategory> categories, LocalDateTime startDate, LocalDateTime endDate, ActivitySort sort);
}
