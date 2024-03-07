package com.stumeet.server.common.util.model;

import java.util.Arrays;

public enum ImageContentType {
	JPG("image/jpg"),
	JPEG("image/jpeg"),
	PNG("image/png")
	;

	private final String contentType;

	ImageContentType(String contentType) {
		this.contentType = contentType;
	}

	private String getContentType() {
		return contentType;
	}

	public static boolean hasContentType(String contentType) {
		return Arrays.stream(ImageContentType.values())
             .anyMatch(type -> type.getContentType().equalsIgnoreCase(contentType));
	}
}
