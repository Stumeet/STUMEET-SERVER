package com.stumeet.server.activity.domain.model;

import java.time.LocalDateTime;

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
