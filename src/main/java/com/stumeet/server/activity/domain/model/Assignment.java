package com.stumeet.server.activity.domain.model;

import lombok.Builder;

import java.time.LocalDateTime;

public class Assignment extends Activity {

    private ActivityPeriod period;

    @Builder
    protected Assignment(Long id, ActivityLinkedStudy study, ActivityMember author, ActivityCategory category, String title, String content, boolean isNotice, LocalDateTime startDate, LocalDateTime endDate, String link, LocalDateTime createdAt) {
        super(id, study, author, category, title, content, link, null, startDate, endDate, isNotice, createdAt);

        this.period = ActivityPeriod.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
