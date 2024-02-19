package com.stumeet.server.study.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Study {
    private Long id;

    private String name;

    private String region;

    private Double rule;

    private String image;

    private int headcount;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean isDeleted;

    private LocalDateTime deletedAt;
}
