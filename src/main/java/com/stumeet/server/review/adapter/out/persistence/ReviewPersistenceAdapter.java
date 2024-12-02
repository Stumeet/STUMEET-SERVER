package com.stumeet.server.review.adapter.out.persistence;

import java.util.List;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.review.adapter.out.persistence.entity.ReviewJpaEntity;
import com.stumeet.server.review.adapter.out.persistence.entity.ReviewTagJpaEntity;
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
    private final JpaReviewTagRepository jpaReviewTagRepository;
    private final JpaReviewTagUsageRepository jpaReviewTagUsageRepository;

    private final ReviewPersistenceMapper reviewPersistenceMapper;

    @Override
    public void save(Review review) {
        ReviewJpaEntity entity = reviewPersistenceMapper.toEntity(review);
        entity = jpaReviewRepository.save(entity);

        List<ReviewTagJpaEntity> reviewTagEntities = findReviewTags(review.getReviewTags());
        saveReviewTags(entity, reviewTagEntities);
    }

    public List<ReviewTagJpaEntity> findReviewTags(List<ReviewTag> reviewTags) {
        return reviewTags.stream()
            .map(tag -> jpaReviewTagRepository.findByTagName(tag.getTagName()))
            .toList();
    }

    public void saveReviewTags(ReviewJpaEntity review, List<ReviewTagJpaEntity> reviewTags) {
        List<ReviewTagUsageJpaEntity> reviewTagUsageJpaEntities = reviewTags.stream()
            .map(reviewTag -> ReviewTagUsageJpaEntity.builder()
                .review(review)
                .reviewTag(reviewTag)
                .build())
            .toList();

        jpaReviewTagUsageRepository.saveAll(reviewTagUsageJpaEntities);
    }

    @Override
    public boolean isExists(Long studyId, Long reviewerId, Long revieweeId) {
        return jpaReviewRepository.existsByStudyIdAndReviewerIdAndRevieweeId(studyId, reviewerId, revieweeId);
    }
}