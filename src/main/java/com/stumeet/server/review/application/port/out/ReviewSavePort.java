package com.stumeet.server.review.application.port.out;

import com.stumeet.server.review.domain.Review;

public interface ReviewSavePort {

    void save(Review review);
}
