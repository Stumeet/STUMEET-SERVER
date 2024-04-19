package com.stumeet.server.stub;

import org.springframework.mock.web.MockMultipartFile;

public class MockMultipartStub {

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
}
