package com.stumeet.server.studymember.application.port.in.response;

public record MyStudyMemberDetailResponse(
        boolean isAdmin,
        boolean canSendGrape,
        Boolean isReviewCompleted
) {
}
