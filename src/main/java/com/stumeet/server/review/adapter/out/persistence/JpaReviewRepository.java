package com.stumeet.server.review.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.review.adapter.out.persistence.entity.ReviewJpaEntity;

public interface JpaReviewRepository extends JpaRepository<ReviewJpaEntity, Long> {

    boolean existsByStudyIdAndReviewerIdAndRevieweeId(Long studyId, Long reviewerId, Long revieweeId);
}
