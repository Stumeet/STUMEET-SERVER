package com.stumeet.server.activity.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

import com.stumeet.server.activity.application.service.model.ActivitySource;
import com.stumeet.server.activity.domain.exception.ActivityManagementAccessDeniedException;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Activity {

    private Long id;

    private ActivityLinkedStudy study;

    private ActivityMember author;

    private ActivityCategory category;

    private String title;

    private String content;

    private String link;

    private String location;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean isNotice;

    private LocalDateTime createdAt;

    public Activity modify(Long memberId, ActivitySource source) {
        validateAuthor(memberId);
        return source.category().create(source);
    }

    private void validateAuthor(Long memberId) {
        if(!isAuthor(memberId)) {
            throw new ActivityManagementAccessDeniedException();
        }
    }

    private boolean isAuthor(Long memberId) {
        return Objects.equals(this.author.getId(), memberId);
    }
}
