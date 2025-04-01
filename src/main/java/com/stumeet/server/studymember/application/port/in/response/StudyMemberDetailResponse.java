package com.stumeet.server.studymember.application.port.in.response;

public record StudyMemberDetailResponse(
        Long id,
        String name,
        String image,
        String region,
        String profession,
        Integer achievement
) {
}
