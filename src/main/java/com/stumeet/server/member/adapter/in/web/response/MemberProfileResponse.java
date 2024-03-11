package com.stumeet.server.member.adapter.in.web.response;

import lombok.Builder;

@Builder
public record MemberProfileResponse(
        Long id,
        String image,
        String nickname,
        String region,
        String profession,
        String tier,
        double experience
) {
}
