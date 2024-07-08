package com.stumeet.server.common.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stumeet.server.common.event.EventPublisher;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class EventConfig {

	private final ApplicationContext applicationContext;

	@Bean
	public InitializingBean eventsInitializer(ApplicationEventPublisher eventPublisher) {
		return () -> EventPublisher.setPublisher(eventPublisher);
	}
}