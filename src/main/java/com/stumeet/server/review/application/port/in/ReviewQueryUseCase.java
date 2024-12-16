package com.stumeet.server.review.application.port.in;

import java.util.List;

import com.stumeet.server.review.adapter.out.web.dto.ReviewDetailResponse;
import com.stumeet.server.review.adapter.out.web.dto.ReviewTagStatsResponse;

public interface ReviewQueryUseCase {

    List<ReviewDetailResponse> getMemberReview(Long memberId, int size, int page, String sortName);

    ReviewTagStatsResponse getMemberReviewTagStats(Long memberId);
}
