package com.stumeet.server.review.adapter.out.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.stumeet.server.review.adapter.out.persistence.entity.ReviewJpaEntity;
import com.stumeet.server.review.domain.ReviewSort;

public interface JpaReviewRepositoryCustom {

    List<ReviewJpaEntity> findByMemberId(Long memberId, Pageable pageable, ReviewSort sort);
}
