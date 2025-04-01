package com.stumeet.server.review.application.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.stumeet.server.activity.application.port.in.mapper.PageInfoUseCaseMapper;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.model.PageInfoResponse;
import com.stumeet.server.review.adapter.out.web.dto.ReviewDetailResponse;
import com.stumeet.server.review.adapter.out.web.dto.ReviewDetailResponses;
import com.stumeet.server.review.adapter.out.web.dto.ReviewStatsResponse;
import com.stumeet.server.review.adapter.out.web.dto.ReviewTagCountStatsResponse;
import com.stumeet.server.review.application.port.in.ReviewQueryUseCase;
import com.stumeet.server.review.application.port.out.ReviewQueryPort;
import com.stumeet.server.review.domain.Review;
import com.stumeet.server.review.domain.ReviewSort;
import com.stumeet.server.review.domain.ReviewTag;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ReviewQueryService implements ReviewQueryUseCase {

    private final ReviewQueryPort reviewQueryPort;
    private final PageInfoUseCaseMapper pageInfoUseCaseMapper;

    @Override
    public ReviewDetailResponses getMemberReview(Long memberId, int size, int page, String sortName) {
        ReviewSort sort = ReviewSort.valueOf(sortName);
        Page<Review> reviews = reviewQueryPort.findMemberReviews(memberId, size, page, sort);

        List<ReviewDetailResponse> items = reviews.stream()
            .map(review -> new ReviewDetailResponse(
                review.getId(),
                review.getRate(),
                review.getContent(),
                review.getCreatedAt(),
                review.getReviewTags().stream()
                    .map(ReviewTag::getTagName)
                    .toList()
            ))
            .toList();

        return ReviewDetailResponses.builder()
                .items(items)
                .pageInfo(pageInfoUseCaseMapper.toPageInfoResponse(reviews))
                .build();
    }

    @Override
    public ReviewStatsResponse getReviewStats(Long memberId) {
        long reviewTagCount = reviewQueryPort.getMemberReviewTagCount(memberId);
        List<ReviewTagCountStatsResponse> reviewTagStats = getMemberReviewTagStats(memberId);

        return ReviewStatsResponse.builder()
                .totalCount(reviewTagCount)
                .tagCountStats(reviewTagStats)
                .build();
    }

    @Override
    public List<ReviewTagCountStatsResponse> getMemberReviewTagStats(Long memberId) {
        return reviewQueryPort.countMemberReviewTags(memberId);
    }

    @Override
    public long getStudyMemberReviewCount(Long studyId, Long memberId) {
        return reviewQueryPort.getStudyMemberReviewCount(studyId, memberId);
    }

    @Override
    public long getMemberReviewTagCount(Long memberId) {
        return reviewQueryPort.getMemberReviewTagCount(memberId);
    }
}
