package com.stumeet.server.activity.adapter.out.persistence;

import static com.stumeet.server.activity.adapter.out.model.QActivityJpaEntity.*;
import static com.stumeet.server.activity.adapter.out.model.QActivityLinkedStudyJpaEntity.*;
import static com.stumeet.server.activity.adapter.out.model.QActivityParticipantJpaEntity.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponse;
import com.stumeet.server.activity.adapter.in.response.QActivityListBriefResponse;
import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.activity.domain.model.ActivitySort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaActivityRepositoryCustomImpl implements JpaActivityRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Page<ActivityJpaEntity> findDetailPagesByCondition(
            Pageable pageable, Boolean isNotice, Long studyId, List<ActivityCategory> categories, ActivitySort sort) {
        List<ActivityJpaEntity> content = query
                .selectFrom(activityJpaEntity)
                .join(activityJpaEntity.study, activityLinkedStudyJpaEntity).fetchJoin()
                .where(
                        isNoticeEq(isNotice),
                        studyIdEq(studyId),
                        categoriesEq(categories)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderBy(sort).toArray(OrderSpecifier[]::new))
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(activityJpaEntity.count())
                .from(activityJpaEntity)
                .where(
                        isNoticeEq(isNotice),
                        studyIdEq(studyId),
                        categoriesEq(categories)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<ActivityListBriefResponse> findBriefsByConditionWithPagination(Pageable pageable, Boolean isNotice,
            Long memberId, Long studyId, ActivityCategory category, LocalDateTime fromDate, LocalDateTime toDate) {
        List<ActivityListBriefResponse> content = query
                .select(
                        new QActivityListBriefResponse(
                                activityJpaEntity.id,
                                activityLinkedStudyJpaEntity.id,
                                activityLinkedStudyJpaEntity.name,
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
                .join(activityJpaEntity.study, activityLinkedStudyJpaEntity)
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
                .orderBy(activityJpaEntity.createdAt.desc())
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
    public List<ActivityListBriefResponse> findBriefsByCondition(Boolean isNotice, Long memberId, Long studyId, ActivityCategory category,
            LocalDateTime fromDate, LocalDateTime toDate) {
        return query
                .select(
                        new QActivityListBriefResponse(
                                activityJpaEntity.id,
                                activityLinkedStudyJpaEntity.id,
                                activityLinkedStudyJpaEntity.name,
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
                .join(activityJpaEntity.study, activityLinkedStudyJpaEntity)
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
                .orderBy(activityJpaEntity.createdAt.desc())
                .fetch();
    }

    private BooleanExpression alwaysTrue() {
        return Expressions.asBoolean(true).isTrue();
    }

    private BooleanExpression isNoticeEq(Boolean isNotice) {
        return isNotice != null ? activityJpaEntity.isNotice.eq(isNotice) : null;
    }

    private BooleanExpression studyIdEq(Long studyId) {
        return studyId != null ? activityJpaEntity.study.id.eq(studyId) : null;
    }

    private BooleanExpression categoryEq(ActivityCategory category) {
        return category != null ? activityJpaEntity.category.eq(category) : alwaysTrue();
    }

    private BooleanExpression categoriesEq(List<ActivityCategory> categories) {
        if (categories == null || categories.isEmpty()) {
            return alwaysTrue();
        }

        return activityJpaEntity.category.in(categories);
    }

    private BooleanExpression startDateBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        return startDateGoe(fromDate).and(startDateLoe(toDate));
    }

    private BooleanExpression startDateGoe(LocalDateTime fromDate) {
        return fromDate != null ? activityJpaEntity.startDate.goe(fromDate) : alwaysTrue();
    }

    private BooleanExpression startDateLoe(LocalDateTime toDate) {
        return toDate != null ? activityJpaEntity.startDate.loe(toDate) : alwaysTrue();
    }

    private BooleanExpression endDateBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        return endDateGoe(fromDate).and(endDateLoe(toDate));
    }

    private BooleanExpression endDateGoe(LocalDateTime fromDate) {
        return fromDate != null ? activityJpaEntity.endDate.goe(fromDate) : alwaysTrue();
    }

    private BooleanExpression endDateLoe(LocalDateTime toDate) {
        return toDate != null ? activityJpaEntity.endDate.loe(toDate) : alwaysTrue();
    }

    private BooleanExpression createdAtBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        return createdAtGoe(fromDate).and(createdAtLoe(toDate));
    }

    private BooleanExpression createdAtGoe(LocalDateTime fromDate) {
        return fromDate != null ? activityJpaEntity.createdAt.goe(fromDate) : alwaysTrue();
    }

    private BooleanExpression createdAtLoe(LocalDateTime toDate) {
        return toDate != null ? activityJpaEntity.createdAt.loe(toDate) : alwaysTrue();
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

    private List<OrderSpecifier<LocalDateTime>> orderBy(ActivitySort sort) {
        return switch (sort) {
            case SPECIFIC -> List.of(
                        new CaseBuilder()
                                .when(categoryEq(ActivityCategory.MEET)).then(activityJpaEntity.startDate)
                                .when(categoryEq(ActivityCategory.ASSIGNMENT)).then(activityJpaEntity.endDate)
                                .otherwise(activityJpaEntity.createdAt)
                                .asc(),
                        activityJpaEntity.createdAt.desc());
            case null, default -> List.of(activityJpaEntity.createdAt.desc());
        };
    }
}

