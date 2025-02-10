package com.stumeet.server.review.application.service;

import java.util.List;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.review.adapter.out.web.dto.ReviewDetailResponse;
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

    @Override
    public List<ReviewDetailResponse> getMemberReview(Long memberId, int size, int page, String sortName) {
        ReviewSort sort = ReviewSort.valueOf(sortName);
        List<Review> reviews = reviewQueryPort.findMemberReviews(memberId, size, page, sort);

        return reviews.stream()
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
    }

    @Override
    public List<ReviewTagCountStatsResponse> getMemberReviewTagStats(Long memberId) {
        List<ReviewTagCountStatsResponse> reviewTagsCnt = reviewQueryPort.countMemberReviewTags(memberId);
        return reviewTagsCnt;
    }

    @Override
    public long getStudyMemberReviewCount(Long studyId, Long memberId) {
        return reviewQueryPort.getStudyMemberReviewCount(studyId, memberId);
    }
}
