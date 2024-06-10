package com.stumeet.server.activity.adapter.out.persistence;

import static com.stumeet.server.activity.adapter.out.model.QActivityJpaEntity.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import com.stumeet.server.activity.domain.model.ActivityCategory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaActivityRepositoryCustomImpl implements JpaActivityRepositoryCustom{

	private final JPAQueryFactory query;

	@Override
	public Page<ActivityJpaEntity> findDetailPagesByCondition(
			Pageable pageable, Boolean isNotice, Long studyId, ActivityCategory category) {
		List<ActivityJpaEntity> content = query
				.selectFrom(activityJpaEntity)
				.where(
						isNotice != null ? activityJpaEntity.isNotice.eq(isNotice) : null,
						studyId != null ? activityJpaEntity.study.id.eq(studyId) : null,
						category != null ? activityJpaEntity.category.eq(category) : null)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(activityJpaEntity.createdAt.desc())
				.fetch();

		JPAQuery<Long> countQuery = query
				.select(activityJpaEntity.count())
				.from(activityJpaEntity)
				.where(
						isNotice != null ? activityJpaEntity.isNotice.eq(isNotice) : null,
						studyId != null ? activityJpaEntity.study.id.eq(studyId) : null,
						category != null ? activityJpaEntity.category.eq(category) : null);

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}
}
