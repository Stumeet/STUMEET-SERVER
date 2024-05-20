package com.stumeet.server.common.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider {

	public LocalDate getCurrentDate() {
		return LocalDate.now();
	}
}
