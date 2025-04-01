package com.stumeet.server.studymember.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Grape {
    private Long id;
    private Long memberId;
    private Long studyId;
    private String studyName;
    private ComplimentType complimentType;
    private LocalDateTime createdAt;

    public static Grape create(Long memberId, Long studyId, String studyName, ComplimentType complimentType) {
        return Grape.builder()
            .memberId(memberId)
            .studyId(studyId)
            .studyName(studyName)
            .complimentType(complimentType)
            .build();
    }
}
