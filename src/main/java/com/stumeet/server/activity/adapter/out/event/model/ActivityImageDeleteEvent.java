package com.stumeet.server.activity.adapter.out.event.model;

import com.stumeet.server.common.event.model.Event;

import lombok.Getter;

@Getter
public class ActivityImageDeleteEvent extends Event {

	private final Long studyId;
	private final Long activityId;

	public ActivityImageDeleteEvent(Object source, Long studyId, Long activityId) {
		super(source, String.format("[활동 이미지 삭제] studyId: %d, activityId: %d", studyId, activityId));
		this.studyId = studyId;
		this.activityId = activityId;
	}
}
