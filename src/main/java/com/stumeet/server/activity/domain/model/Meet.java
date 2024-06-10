package com.stumeet.server.activity.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Meet extends Activity {

    @Builder
    protected Meet(Long id, ActivityLinkedStudy study, ActivityMember author, ActivityCategory category, String title, String content, boolean isNotice, LocalDateTime startDate, LocalDateTime endDate, String location, LocalDateTime createdAt) {
        super(id, study, author, category, title, content, isNotice, startDate, endDate, location, createdAt);
    }
}
