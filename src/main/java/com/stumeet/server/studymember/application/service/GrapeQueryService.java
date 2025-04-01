package com.stumeet.server.studymember.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.activity.application.port.in.mapper.PageInfoUseCaseMapper;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.review.application.port.out.GrapeQueryPort;
import com.stumeet.server.studymember.adapter.in.web.dto.GrapeResponse;
import com.stumeet.server.studymember.adapter.in.web.dto.GrapeResponses;
import com.stumeet.server.studymember.application.port.in.MemberGrapeQueryUseCase;
import com.stumeet.server.studymember.domain.Grape;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GrapeQueryService implements MemberGrapeQueryUseCase {

    private final GrapeQueryPort grapeQueryPort;
    private final PageInfoUseCaseMapper pageInfoUseCaseMapper;

    @Override
    public GrapeResponses findMemberGrapes(Long memberId, int page, int size) {
        Page<Grape> grapePages = grapeQueryPort.findPageByMemberId(memberId, page, size);
        List<GrapeResponse> grapes = mapToGrapeResponse(grapePages);

        return GrapeResponses.builder()
            .grapes(grapes)
            .pageInfo(pageInfoUseCaseMapper.toPageInfoResponse(grapePages))
            .build();
    }

    @Override
    public int countMemberGrape(Long memberId) {
        return grapeQueryPort.countMemberGrapes(memberId);
    }

    private List<GrapeResponse> mapToGrapeResponse(Page<Grape> pages) {
        return pages.map(grape -> GrapeResponse.builder()
                .id(grape.getId())
                .studyName(grape.getStudyName())
                .compliment(grape.getComplimentType().getDescription())
                .createdAt(grape.getCreatedAt())
                .build())
            .stream().toList();
    }
}
