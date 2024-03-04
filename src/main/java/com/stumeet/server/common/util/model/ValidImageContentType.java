package com.stumeet.server.common.util.model;

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
		for (ValidImageContentType type : ValidImageContentType.values()) {
			if(type.getContentType().equalsIgnoreCase(contentType)) {
				return true;
			}
		}
		return false;
	}
}
