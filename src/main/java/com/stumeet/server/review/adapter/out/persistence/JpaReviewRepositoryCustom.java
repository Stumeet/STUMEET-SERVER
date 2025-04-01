package com.stumeet.server.review.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stumeet.server.review.adapter.out.persistence.entity.ReviewJpaEntity;
import com.stumeet.server.review.domain.ReviewSort;

public interface JpaReviewRepositoryCustom {

    Page<ReviewJpaEntity> findByMemberId(Long memberId, Pageable pageable, ReviewSort sort);
}
