package com.stumeet.server.stub;

import java.util.List;

import com.stumeet.server.review.adapter.out.web.dto.ReviewRegisterRequest;

public class ReviewStub {

    public static ReviewRegisterRequest getReviewRegisterRequest() {
        return new ReviewRegisterRequest(
            2L,
            1L,
            5,
            "성실하게 참여해주셨습니다.",
            List.of("TASK_COMMITMENT", "CONSISTENT_ATTENDANCE")
        );
    }

    public static ReviewRegisterRequest getNotExistStudyReviewRegisterRequest() {
        return new ReviewRegisterRequest(
            2L,
            0L,
            5,
            "성실하게 참여해주셨습니다.",
            List.of("TASK_COMMITMENT", "CONSISTENT_ATTENDANCE")
        );
    }

    public static ReviewRegisterRequest getRevieweeNotJoinedReviewRegisterRequest() {
        return new ReviewRegisterRequest(
            3L,
            1L,
            5,
            "성실하게 참여해주셨습니다.",
            List.of("TASK_COMMITMENT", "CONSISTENT_ATTENDANCE")
        );
    }

    public static ReviewRegisterRequest getRateOutOfRangeReviewRegisterRequest() {
        return new ReviewRegisterRequest(
            2L,
            1L,
            10,
            "성실하게 참여해주셨습니다.",
            List.of("TASK_COMMITMENT", "CONSISTENT_ATTENDANCE")
        );
    }

    public static ReviewRegisterRequest getInvalidReviewTagCountReviewRegisterRequest() {
        return new ReviewRegisterRequest(
            2L,
            1L,
            10,
            "성실하게 참여해주셨습니다.",
            List.of("TASK_COMMITMENT", "CONSISTENT_ATTENDANCE")
        );
    }

    public static ReviewRegisterRequest getAlreadyReviewedRegisterRequest() {
        return new ReviewRegisterRequest(
            4L,
            1L,
            5,
            "성실하게 참여해주셨습니다.",
            List.of("TASK_COMMITMENT", "CONSISTENT_ATTENDANCE")
        );
    }
}
