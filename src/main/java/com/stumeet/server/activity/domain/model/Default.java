package com.stumeet.server.activity.domain.model;

import lombok.Builder;

import java.time.LocalDateTime;


public class Default extends Activity {

    @Builder
    protected Default(Long id, ActivityLinkedStudy study, ActivityMember author, ActivityCategory category, String title, String content, String link, boolean isNotice, LocalDateTime createdAt) {
        super(id, study, author, category, title, content, link, null, null, null, isNotice,  createdAt);
    }
}
