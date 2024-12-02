package com.stumeet.server.review.adapter.out.persistence;

import java.util.List;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.review.adapter.out.persistence.entity.ReviewJpaEntity;
import com.stumeet.server.review.adapter.out.persistence.entity.ReviewTagUsageJpaEntity;
import com.stumeet.server.review.adapter.out.persistence.mapper.ReviewPersistenceMapper;
import com.stumeet.server.review.application.port.out.ReviewQueryPort;
import com.stumeet.server.review.application.port.out.ReviewSavePort;
import com.stumeet.server.review.domain.Review;
import com.stumeet.server.review.domain.ReviewTag;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReviewPersistenceAdapter implements ReviewSavePort, ReviewQueryPort {

    private final JpaReviewRepository jpaReviewRepository;
    private final JpaReviewTagUsageRepository jpaReviewTagUsageRepository;

    private final ReviewPersistenceMapper reviewPersistenceMapper;

    @Override
    public void save(Review review) {
        ReviewJpaEntity entity = reviewPersistenceMapper.toEntity(review);
        entity = jpaReviewRepository.save(entity);

        saveReviewTagUsages(entity, review.getReviewTags());
    }

    public void saveReviewTagUsages(ReviewJpaEntity review, List<ReviewTag> reviewTags) {
        List<ReviewTagUsageJpaEntity> reviewTagUsages = reviewTags.stream()
            .map(reviewTag -> ReviewTagUsageJpaEntity.builder()
                .review(review)
                .reviewTag(reviewTag)
                .build())
            .toList();

        jpaReviewTagUsageRepository.saveAll(reviewTagUsages);
    }

    @Override
    public boolean isExists(Long studyId, Long reviewerId, Long revieweeId) {
        return jpaReviewRepository.existsByStudyIdAndReviewerIdAndRevieweeId(studyId, reviewerId, revieweeId);
    }
}