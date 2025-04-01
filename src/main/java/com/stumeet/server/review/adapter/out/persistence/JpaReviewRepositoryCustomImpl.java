package com.stumeet.server.review.adapter.out.persistence;

import java.util.List;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.review.adapter.out.persistence.entity.ReviewJpaEntity;
import com.stumeet.server.review.domain.ReviewSort;

import lombok.RequiredArgsConstructor;

import static com.stumeet.server.activity.adapter.out.model.QActivityJpaEntity.*;
import static com.stumeet.server.activity.adapter.out.model.QActivityParticipantJpaEntity.*;
import static com.stumeet.server.review.adapter.out.persistence.entity.QReviewJpaEntity.reviewJpaEntity;
import static com.stumeet.server.review.adapter.out.persistence.entity.QReviewTagJpaEntity.reviewTagJpaEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@PersistenceAdapter
@RequiredArgsConstructor
public class JpaReviewRepositoryCustomImpl implements JpaReviewRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Page<ReviewJpaEntity> findByMemberId(Long memberId, Pageable pageable, ReviewSort sort) {
        List<ReviewJpaEntity> content = query.selectFrom(reviewJpaEntity)
            .leftJoin(reviewJpaEntity.tags, reviewTagJpaEntity).fetchJoin()
            .where(
                reviewJpaEntity.revieweeId.eq(memberId)
            )
            .orderBy(toOrderSpecifiers(sort))
            .orderBy(reviewJpaEntity.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Long> countQuery = query
                .select(reviewJpaEntity.count())
                .from(reviewJpaEntity)
                .leftJoin(reviewJpaEntity.tags, reviewTagJpaEntity)
                .where(
                        reviewJpaEntity.revieweeId.eq(memberId)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private OrderSpecifier<?> toOrderSpecifiers(ReviewSort sort) {
        return switch (sort) {
            case LATEST -> {
                yield reviewJpaEntity.createdAt.desc();
            }
            case HIGHEST_RATING -> {
                yield reviewJpaEntity.rate.desc();
            }
            case LOWEST_RATING -> {
                yield reviewJpaEntity.rate.asc();
            }
        };
    }
}
