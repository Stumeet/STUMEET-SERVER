package com.stumeet.server.activity.adapter.out.persistence;

import static com.stumeet.server.activity.adapter.out.model.QActivityJpaEntity.*;
import static com.stumeet.server.activity.adapter.out.model.QActivityParticipantJpaEntity.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponse;
import com.stumeet.server.activity.adapter.in.response.QActivityListBriefResponse;
import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import com.stumeet.server.activity.domain.model.ActivityCategory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaActivityRepositoryCustomImpl implements JpaActivityRepositoryCustom {

	private final JPAQueryFactory query;

	@Override
	public Page<ActivityJpaEntity> findDetailPagesByCondition(
			Pageable pageable, Boolean isNotice, Long studyId, ActivityCategory category) {
		List<ActivityJpaEntity> content = query
				.selectFrom(activityJpaEntity)
				.where(
						isNoticeEq(isNotice),
						studyIdEq(studyId),
						categoryEq(category)
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(activityJpaEntity.createdAt.desc())
				.fetch();

		JPAQuery<Long> countQuery = query
				.select(activityJpaEntity.count())
				.from(activityJpaEntity)
				.where(
						isNoticeEq(isNotice),
						studyIdEq(studyId),
						categoryEq(category)
				);

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	@Override
	public Page<ActivityListBriefResponse> findBriefsByConditionWithPagination(Pageable pageable, Boolean isNotice, Long memberId,
			Long studyId, ActivityCategory category, LocalDateTime fromDate, LocalDateTime toDate) {
		List<ActivityListBriefResponse> content = query
				.select(
						new QActivityListBriefResponse(
								activityJpaEntity.id,
								activityJpaEntity.category.stringValue(),
								activityJpaEntity.title,
								activityJpaEntity.startDate,
								activityJpaEntity.endDate,
								activityJpaEntity.location,
								activityParticipantJpaEntity.status,
								activityJpaEntity.createdAt
						)
				)
				.from(activityJpaEntity)
				.leftJoin(activityParticipantJpaEntity)
				.on(
						activityJpaEntity.id.eq(activityParticipantJpaEntity.activity.id)
								.and(activityParticipantJpaEntity.member.id.eq(memberId))
				)
				.where(
						isNoticeEq(isNotice),
						studyIdEq(studyId),
						categoryEq(category),
						dateBetweenByCategory(category, fromDate, toDate)
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(orderByCategory(category))
				.fetch();

		JPAQuery<Long> countQuery = query
				.select(activityJpaEntity.count())
				.from(activityJpaEntity)
				.leftJoin(activityParticipantJpaEntity)
				.on(
						activityJpaEntity.id.eq(activityParticipantJpaEntity.activity.id)
								.and(activityParticipantJpaEntity.member.id.eq(memberId))
				)
				.where(
						isNoticeEq(isNotice),
						studyIdEq(studyId),
						categoryEq(category),
						dateBetweenByCategory(category, fromDate, toDate)
				);

		return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
	}

	@Override
	public List<ActivityListBriefResponse> findBriefsByCondition(Boolean isNotice, Long memberId, Long studyId,
			ActivityCategory category, LocalDateTime fromDate, LocalDateTime toDate) {
		return query
				.select(
						new QActivityListBriefResponse(
								activityJpaEntity.id,
								activityJpaEntity.category.stringValue(),
								activityJpaEntity.title,
								activityJpaEntity.startDate,
								activityJpaEntity.endDate,
								activityJpaEntity.location,
								activityParticipantJpaEntity.status,
								activityJpaEntity.createdAt
						)
				)
				.from(activityJpaEntity)
				.leftJoin(activityParticipantJpaEntity)
				.on(
						activityJpaEntity.id.eq(activityParticipantJpaEntity.activity.id)
								.and(activityParticipantJpaEntity.member.id.eq(memberId))
				)
				.where(
						isNoticeEq(isNotice),
						studyIdEq(studyId),
						categoryEq(category),
						dateBetweenByCategory(category, fromDate, toDate)
				)
				.orderBy(orderByCategory(category))
				.fetch();
	}

	private BooleanExpression isNoticeEq(Boolean isNotice) {
		return isNotice != null ? activityJpaEntity.isNotice.eq(isNotice) : null;
	}

	private BooleanExpression studyIdEq(Long studyId) {
		return studyId != null ? activityJpaEntity.study.id.eq(studyId) : null;
	}

	private BooleanExpression categoryEq(ActivityCategory category) {
		return category != null ? activityJpaEntity.category.eq(category) : null;
	}

	private BooleanExpression startDateBetween(LocalDateTime fromDate, LocalDateTime toDate) {
		return startDateGoe(fromDate).and(startDateLoe(toDate));
	}

	private BooleanExpression startDateGoe(LocalDateTime fromDate) {
		return fromDate != null ? activityJpaEntity.startDate.goe(fromDate) : Expressions.asBoolean(true).isTrue();
	}

	private BooleanExpression startDateLoe(LocalDateTime toDate) {
		return toDate != null ? activityJpaEntity.startDate.loe(toDate) : Expressions.asBoolean(true).isTrue();
	}

	private BooleanExpression endDateBetween(LocalDateTime fromDate, LocalDateTime toDate) {
		return endDateGoe(fromDate).and(endDateLoe(toDate));
	}

	private BooleanExpression endDateGoe(LocalDateTime fromDate) {
		return fromDate != null ? activityJpaEntity.endDate.goe(fromDate) : Expressions.asBoolean(true).isTrue();
	}

	private BooleanExpression endDateLoe(LocalDateTime toDate) {
		return toDate != null ? activityJpaEntity.endDate.loe(toDate) : Expressions.asBoolean(true).isTrue();
	}

	private BooleanExpression createdAtBetween(LocalDateTime fromDate, LocalDateTime toDate) {
		return createdAtGoe(fromDate).and(createdAtLoe(toDate));
	}

	private BooleanExpression createdAtGoe(LocalDateTime fromDate) {
		return fromDate != null ? activityJpaEntity.createdAt.goe(fromDate) : Expressions.asBoolean(true).isTrue();
	}

	private BooleanExpression createdAtLoe(LocalDateTime toDate) {
		return toDate != null ? activityJpaEntity.createdAt.loe(toDate) : Expressions.asBoolean(true).isTrue();
	}

	private BooleanExpression dateBetweenByCategory(ActivityCategory category, LocalDateTime fromDate, LocalDateTime toDate) {
		if (category == null) {
			return categoryEq(ActivityCategory.MEET).and(startDateBetween(fromDate, toDate))
					.or(categoryEq(ActivityCategory.ASSIGNMENT).and(endDateBetween(fromDate, toDate)))
					.or(categoryEq(ActivityCategory.DEFAULT).and(createdAtBetween(fromDate, toDate)));
		}

		return switch (category) {
			case MEET -> startDateBetween(fromDate, toDate);
			case ASSIGNMENT -> endDateBetween(fromDate, toDate);
			case DEFAULT -> createdAtBetween(fromDate, toDate);
		};
	}

	private OrderSpecifier<LocalDateTime> orderByCategory(ActivityCategory category) {
		if (category == null) {
			return activityJpaEntity.createdAt.desc();
		}

		return switch (category) {
			case MEET -> activityJpaEntity.startDate.desc();
			case ASSIGNMENT -> activityJpaEntity.endDate.desc();
			case DEFAULT -> activityJpaEntity.createdAt.desc();
		};
	}
}

