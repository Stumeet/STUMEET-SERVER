package com.stumeet.server.studymember.adapter.in.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record GrapeSendRequest(
    @NotNull
    Long studyMemberId,

    @NotEmpty
    String complimentType
) {
}
