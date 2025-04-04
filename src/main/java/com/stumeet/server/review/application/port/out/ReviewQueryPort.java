package com.stumeet.server.review.application.port.out;

import java.util.List;

import org.springframework.data.domain.Page;

import com.stumeet.server.review.adapter.out.web.dto.ReviewTagCountStatsResponse;
import com.stumeet.server.review.domain.Review;
import com.stumeet.server.review.domain.ReviewSort;

public interface ReviewQueryPort {

    boolean isExists(Long studyId, Long reviewerId, Long revieweeId);

    Page<Review> findMemberReviews(Long memberId, Integer size, Integer page, ReviewSort sort);

    List<ReviewTagCountStatsResponse> countMemberReviewTags(Long memberId);

    long getStudyMemberReviewCount(Long studyId, Long memberId);

    long getMemberReviewTagCount(Long memberId);
}
