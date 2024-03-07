package com.stumeet.server.common.util.model;

import java.util.Arrays;

public enum ValidImageContentType {
	JPG("image/jpg"),
	JPEG("image/jpeg"),
	PNG("image/png")
	;

	private final String contentType;

	ValidImageContentType(String contentType) {
		this.contentType = contentType;
	}

	private String getContentType() {
		return contentType;
	}

	public static boolean hasContentType(String contentType) {
		return Arrays.stream(ValidImageContentType.values())
             .anyMatch(type -> type.getContentType().equalsIgnoreCase(contentType));
	}
}
