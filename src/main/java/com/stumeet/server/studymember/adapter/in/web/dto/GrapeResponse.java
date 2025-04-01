package com.stumeet.server.studymember.adapter.in.web.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record GrapeResponse(
    Long id,
    String studyName,
    String compliment,
    LocalDateTime createdAt
) {
}
