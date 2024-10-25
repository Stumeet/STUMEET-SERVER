package com.stumeet.server.activity.adapter.out.event.model;

import com.stumeet.server.common.event.model.Event;

import lombok.Getter;

@Getter
public class StudyNoticeNotificationEvent extends Event {

    private final Long studyId;
    private final Long activityId;

    public StudyNoticeNotificationEvent(Object source, Long studyId, Long activityId) {
        super(source, String.format("[스터디 공지 알림 발송] studyId: %d, activityId: %d", studyId, activityId));
        this.studyId = studyId;
        this.activityId = activityId;
    }
}
