package com.stumeet.server.review.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.review.adapter.out.persistence.entity.ReviewJpaEntity;
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
}
