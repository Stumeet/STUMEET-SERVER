package com.stumeet.server.activity.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Activity {

    private Long id;

    private ActivityLinkedStudy study;

    private ActivityMember author;

    private ActivityCategory category;

    private String title;

    private String content;

    private boolean isNotice;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime createdAt;

}
