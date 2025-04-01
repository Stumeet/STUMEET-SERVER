package com.stumeet.server.common.event;

import org.springframework.context.ApplicationEventPublisher;

import com.stumeet.server.common.event.model.Event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventPublisher {

	private static ApplicationEventPublisher publisher;

	public static void setPublisher(ApplicationEventPublisher publisher) {
		EventPublisher.publisher = publisher;
	}

	public static void raise(Event event) {
		if (publisher != null) {
			publisher.publishEvent(event);
		}
	}
}
