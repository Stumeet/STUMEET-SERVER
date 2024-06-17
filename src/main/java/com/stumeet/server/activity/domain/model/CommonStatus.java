package com.stumeet.server.activity.domain.model;

public enum CommonStatus implements ActivityStatus{
	NON_PARTICIPATION("미참여");

	private final String description;

	CommonStatus(String description) {
		this.description = description;
	}

	@Override
	public String getStatus() {
		return this.name();
	}

	@Override
	public String getDescription() {
		return this.description;
	}
}
