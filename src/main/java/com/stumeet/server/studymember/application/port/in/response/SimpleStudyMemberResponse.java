package com.stumeet.server.studymember.application.port.in.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

@Builder
public record SimpleStudyMemberResponse(
    Long id,
    String name,
    String image,
    String region,
    String profession,
    boolean isAdmin
) {
    @QueryProjection
    public SimpleStudyMemberResponse {}
}
