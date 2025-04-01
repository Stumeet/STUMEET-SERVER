package com.stumeet.server.studymember.application.port.in.response;

import com.querydsl.core.annotations.QueryProjection;

public record StudyMemberReviewStatusResponse(
        Long studyMemberId,
        Long memberId,
        String name,
        String region,
        String profession,
        String image,
        Boolean isReviewed
) {
    @QueryProjection
    public StudyMemberReviewStatusResponse {
    }
}
