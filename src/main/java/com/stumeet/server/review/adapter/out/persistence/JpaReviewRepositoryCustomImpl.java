package com.stumeet.server.review.adapter.out.persistence;

import java.util.List;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.review.adapter.out.persistence.entity.ReviewJpaEntity;
import com.stumeet.server.review.domain.ReviewSort;

import lombok.RequiredArgsConstructor;

import static com.stumeet.server.review.adapter.out.persistence.entity.QReviewJpaEntity.reviewJpaEntity;
import static com.stumeet.server.review.adapter.out.persistence.entity.QReviewTagJpaEntity.reviewTagJpaEntity;

import org.springframework.data.domain.Pageable;

@PersistenceAdapter
@RequiredArgsConstructor
public class JpaReviewRepositoryCustomImpl implements JpaReviewRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<ReviewJpaEntity> findByMemberId(Long memberId, Pageable pageable, ReviewSort sort) {
        return query.selectFrom(reviewJpaEntity)
            .leftJoin(reviewJpaEntity.tags, reviewTagJpaEntity).fetchJoin()
            .where(
                reviewJpaEntity.revieweeId.eq(memberId)
            )
            .orderBy(toOrderSpecifiers(sort))
            .orderBy(reviewJpaEntity.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
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
