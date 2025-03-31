package com.stumeet.server.review.adapter.out.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.review.adapter.out.persistence.dto.ReviewTagCountDto;
import com.stumeet.server.review.adapter.out.persistence.entity.ReviewJpaEntity;
import com.stumeet.server.review.adapter.out.persistence.entity.ReviewTagJpaEntity;
import com.stumeet.server.review.adapter.out.persistence.mapper.ReviewPersistenceMapper;
import com.stumeet.server.review.adapter.out.web.dto.ReviewTagCountStatsResponse;
import com.stumeet.server.review.application.port.out.ReviewQueryPort;
import com.stumeet.server.review.application.port.out.ReviewSavePort;
import com.stumeet.server.review.domain.Review;
import com.stumeet.server.review.domain.ReviewSort;
import com.stumeet.server.review.domain.ReviewTag;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReviewPersistenceAdapter implements ReviewSavePort, ReviewQueryPort {

    private final JpaReviewRepository jpaReviewRepository;
    private final JpaReviewTagRepository jpaReviewTagRepository;
    private final JpaReviewRepositoryCustom jpaReviewRepositoryCustom;

    private final ReviewPersistenceMapper reviewPersistenceMapper;

    @Override
    public void save(Review review) {
        ReviewJpaEntity entity = reviewPersistenceMapper.toEntity(review);
        entity = jpaReviewRepository.save(entity);

        saveReviewTagUsages(entity, review.getReviewTags());
    }

    public void saveReviewTagUsages(ReviewJpaEntity review, List<ReviewTag> reviewTags) {
        List<ReviewTagJpaEntity> reviewTagUsages = reviewTags.stream()
            .map(reviewTag -> ReviewTagJpaEntity.builder()
                .review(review)
                .reviewTag(reviewTag)
                .build())
            .toList();

        jpaReviewTagRepository.saveAll(reviewTagUsages);
    }

    @Override
    public boolean isExists(Long studyId, Long reviewerId, Long revieweeId) {
        return jpaReviewRepository.existsByStudyIdAndReviewerIdAndRevieweeId(studyId, reviewerId, revieweeId);
    }

    @Override
    public Page<Review> findMemberReviews(Long memberId, Integer size, Integer page, ReviewSort sort) {
        Page<ReviewJpaEntity> entities = jpaReviewRepositoryCustom.findByMemberId(memberId, PageRequest.of(page, size), sort);
        return reviewPersistenceMapper.toDomains(entities);
    }

    @Override
    public List<ReviewTagCountStatsResponse> countMemberReviewTags(Long memberId) {
        List<ReviewTagCountDto> result = jpaReviewTagRepository.countReviewTagByRevieweeIdOrdered(memberId);

        return result.stream()
            .map(tagStat -> ReviewTagCountStatsResponse.builder()
                .reviewTagName(tagStat.reviewTag().getTagName())
                .count(tagStat.count())
                .build())
            .toList();
    }

    @Override
    public long getStudyMemberReviewCount(Long studyId, Long memberId) {
        return jpaReviewRepository.countByReviewerIdAndStudyId(memberId, studyId);
    }

    @Override
    public long getMemberReviewTagCount(Long memberId) {
        return jpaReviewTagRepository.countByReview_RevieweeId(memberId);
    }
}