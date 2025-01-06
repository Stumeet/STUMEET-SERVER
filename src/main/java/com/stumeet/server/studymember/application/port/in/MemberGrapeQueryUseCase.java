package com.stumeet.server.studymember.application.port.in;

import com.stumeet.server.studymember.adapter.in.web.dto.GrapeResponses;

public interface MemberGrapeQueryUseCase {

    GrapeResponses findMemberGrapes(Long memberId, int page, int size);

    int countMemberGrape(Long memberId);
}
