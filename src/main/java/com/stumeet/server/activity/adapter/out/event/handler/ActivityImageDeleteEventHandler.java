package com.stumeet.server.activity.adapter.out.event.handler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.stumeet.server.activity.adapter.out.event.model.ActivityImageDeleteEvent;
import com.stumeet.server.file.application.port.in.FileDeleteUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class ActivityImageDeleteEventHandler {

	private final FileDeleteUseCase fileDeleteUseCase;

	@Async
	@EventListener(ActivityImageDeleteEvent.class)
	public void deleteActivityImage(ActivityImageDeleteEvent event) {
		log.info(event.getMessage());
		fileDeleteUseCase.deleteActivityRelatedImage(event.getStudyId(), event.getActivityId());
	}
}
