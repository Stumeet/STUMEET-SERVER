package com.stumeet.server.review.application.port.out;

import org.springframework.data.domain.Page;

import com.stumeet.server.studymember.domain.Grape;

public interface GrapeQueryPort {

    Page<Grape> findPageByMemberId(Long memberId, int page, int size);

    int countMemberGrapes(Long memberId);
}
