package com.stumeet.server.activity.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import com.stumeet.server.activity.domain.model.ActivityCategory;

public interface JpaActivityRepositoryCustom {
	Page<ActivityJpaEntity> findDetailPagesByCondition(Pageable pageable, Boolean isNotice, Long studyId, ActivityCategory category);
}
