package com.stumeet.server.studymember.adapter.in.web.dto;

import java.util.List;

import com.stumeet.server.common.model.PageInfoResponse;

import lombok.Builder;

@Builder
public record GrapeResponses(
    List<GrapeResponse> grapes,
    PageInfoResponse pageInfo
) {
}
