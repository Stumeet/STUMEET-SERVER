package com.stumeet.server.review.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.review.adapter.out.persistence.entity.ReviewTagUsageJpaEntity;

public interface JpaReviewTagUsageRepository extends JpaRepository<ReviewTagUsageJpaEntity, Long> {
}
