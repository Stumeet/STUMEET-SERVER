package com.stumeet.server.common.event.model;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class Event extends ApplicationEvent {

	private final String message;

	protected Event(Object source, String message) {
		super(source);
		this.message = message;
	}

	public static Event of(Object source, String message) {
		return new Event(source, message);
	}
}