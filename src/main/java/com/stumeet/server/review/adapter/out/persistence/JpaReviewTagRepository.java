package com.stumeet.server.review.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.review.adapter.out.persistence.entity.ReviewTagJpaEntity;

public interface JpaReviewTagRepository extends JpaRepository<ReviewTagJpaEntity, Long> {
}
