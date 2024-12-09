package com.stumeet.server.review.application.port.out;

import java.util.List;

import com.stumeet.server.review.domain.Review;
import com.stumeet.server.review.domain.ReviewSort;

public interface ReviewQueryPort {

    boolean isExists(Long studyId, Long reviewerId, Long revieweeId);

    List<Review> findMemberReviews(Long memberId, Integer size, Integer page, ReviewSort sort);
}
