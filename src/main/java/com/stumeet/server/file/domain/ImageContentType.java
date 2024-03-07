package com.stumeet.server.file.domain;

import java.util.Arrays;

public enum ImageContentType {
	JPG("image/jpeg", "jpg"),
	JPEG("image/jpeg", "jpeg"),
	PNG("image/png", "png");

	private final String contentType;
	private final String extension;

	ImageContentType(String contentType, String extension) {
		this.contentType = contentType;
		this.extension = extension;
	}

	private String getContentType() {
		return contentType;
	}

	public String getExtension() {
		return extension;
	}

	public static boolean isValid(String contentType, String extension) {
		return Arrays.stream(ImageContentType.values())
			.anyMatch(type ->
				type.getExtension().equalsIgnoreCase(extension)
				&& type.getContentType().equalsIgnoreCase(contentType));
	}
}
