package com.stumeet.server.activity.domain.model;

import lombok.Builder;

import java.time.LocalDateTime;

public class Assignment extends Activity {

    @Builder
    protected Assignment(Long id, ActivityLinkedStudy study, ActivityMember author, ActivityCategory category, String title, String content, boolean isNotice, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt) {
        super(id, study, author, category, title, content, isNotice, startDate, endDate, null, createdAt);
    }
}
