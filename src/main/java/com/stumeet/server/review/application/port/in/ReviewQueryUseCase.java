package com.stumeet.server.review.application.port.in;

import java.util.List;

import com.stumeet.server.review.adapter.out.web.dto.ReviewDetailResponse;
import com.stumeet.server.review.adapter.out.web.dto.ReviewStatsResponse;
import com.stumeet.server.review.adapter.out.web.dto.ReviewTagCountStatsResponse;

public interface ReviewQueryUseCase {

    List<ReviewDetailResponse> getMemberReview(Long memberId, int size, int page, String sortName);

    ReviewStatsResponse getReviewStats(Long memberId);

    List<ReviewTagCountStatsResponse> getMemberReviewTagStats(Long memberId);

    long getStudyMemberReviewCount(Long studyId, Long memberId);

    long getMemberReviewTagCount(Long memberId);
}
