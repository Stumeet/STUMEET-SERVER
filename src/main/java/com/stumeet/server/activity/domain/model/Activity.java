package com.stumeet.server.activity.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

import com.stumeet.server.activity.application.service.model.ActivitySource;
import com.stumeet.server.activity.domain.exception.ActivityManagementAccessDeniedException;
import com.stumeet.server.common.exception.model.BadRequestException;
import com.stumeet.server.common.response.ErrorCode;

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

    protected Activity(Long id, ActivityLinkedStudy study, ActivityMember author, ActivityCategory category,
            String title,
            String content, String link, String location, LocalDateTime startDate, LocalDateTime endDate,
            boolean isNotice,
            LocalDateTime createdAt) {
        validatePeriod(category, startDate, endDate);

        this.id = id;
        this.study = study;
        this.author = author;
        this.category = category;
        this.title = title;
        this.content = content;
        this.link = link;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isNotice = isNotice;
        this.createdAt = createdAt;
    }

    private void validatePeriod(ActivityCategory category, LocalDateTime startDate, LocalDateTime endDate) {
        if (ActivityCategory.DEFAULT.equals(category)) {
            return;
        }

        if (startDate == null || endDate == null) {
            throw new BadRequestException(ErrorCode.ACTIVITY_PERIOD_REQUIRED_EXCEPTION);
        }

        if (startDate.isAfter(endDate)) {
            throw new BadRequestException(ErrorCode.INVALID_PERIOD_EXCEPTION);
        }
    }

    public Activity update(Long memberId, ActivitySource source) {
        validateAuthor(memberId);
        return source.category().create(source);
    }

    private void validateAuthor(Long memberId) {
        if (!isAuthor(memberId)) {
            throw new ActivityManagementAccessDeniedException();
        }
    }

    private boolean isAuthor(Long memberId) {
        return Objects.equals(this.author.getId(), memberId);
    }
}
