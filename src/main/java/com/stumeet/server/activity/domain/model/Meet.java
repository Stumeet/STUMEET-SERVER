package com.stumeet.server.activity.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Meet extends Activity {
    private String location;

    @Builder
    protected Meet(Long id, ActivityLinkedStudy study, ActivityMember author, ActivityCategory category, String title, String content, boolean isNotice, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt, String location) {
        super(id, study, author, category, title, content, isNotice, startDate, endDate, createdAt);
        this.location = location;
    }
}
