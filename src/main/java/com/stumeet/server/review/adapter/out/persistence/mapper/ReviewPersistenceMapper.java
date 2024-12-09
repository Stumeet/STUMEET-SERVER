package com.stumeet.server.review.adapter.out.persistence.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stumeet.server.review.adapter.out.persistence.entity.ReviewJpaEntity;
import com.stumeet.server.review.adapter.out.persistence.entity.ReviewTagJpaEntity;
import com.stumeet.server.review.domain.Review;

@Component
public class ReviewPersistenceMapper {

    public ReviewJpaEntity toEntity(Review domain) {
        return ReviewJpaEntity.builder()
            .id(domain.getId())
            .reviewerId(domain.getReviewerId())
            .revieweeId(domain.getRevieweeId())
            .studyId(domain.getStudyId())
            .rate(domain.getRate())
            .content(domain.getContent())
            .build();
    }

    public Review toDomain(ReviewJpaEntity entity) {
        return Review.builder()
            .id(entity.getId())
            .reviewerId(entity.getReviewerId())
            .revieweeId(entity.getRevieweeId())
            .studyId(entity.getStudyId())
            .rate(entity.getRate())
            .content(entity.getContent())
            .reviewTags(entity.getTags().stream()
                .map(ReviewTagJpaEntity::getReviewTag)
                .toList())
            .createdAt(entity.getCreatedAt())
            .build();
    }

    public List<Review> toDomains(List<ReviewJpaEntity> entities) {
        return entities.stream()
            .map(this::toDomain)
            .toList();
    }
}