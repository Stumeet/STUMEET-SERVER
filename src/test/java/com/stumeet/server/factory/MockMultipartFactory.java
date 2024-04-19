package com.stumeet.server.factory;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;

public class MockMultipartFactory {

	public static MockMultipartFile createJpegFile(String parameterName) {
		return new MockMultipartFile(
			parameterName,
			"file.jpeg",
			"image/jpeg",
			"test".getBytes());
	}

	public static MockMultipartFile createJpgFile(String parameterName) {
		return new MockMultipartFile(
			parameterName,
			"file.jpg",
			"image/jpeg",
			"test".getBytes());
	}

	public static MockMultipartFile createPngFile(String parameterName) {
		return new MockMultipartFile(
			parameterName,
			"file.png",
			"image/png",
			"test".getBytes());
	}

	public static MockPart createMockPart(byte[] request) {
		return new MockPart("request", "", request, MediaType.APPLICATION_JSON);
	}
}
