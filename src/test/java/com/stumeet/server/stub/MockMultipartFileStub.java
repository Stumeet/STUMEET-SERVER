package com.stumeet.server.stub;

import org.springframework.mock.web.MockMultipartFile;

public class MockMultipartFileStub {
	public static MockMultipartFile getJpegFile() {
		return new MockMultipartFile(
				"file",
				"file.jpeg",
				"image/jpeg",
				"test".getBytes());
	}

	public static MockMultipartFile getJpgFile() {
		return new MockMultipartFile(
				"file",
				"file.jpg",
				"image/jpeg",
				"test".getBytes());
	}

	public static MockMultipartFile getPngFile() {
		return new MockMultipartFile(
				"file",
				"file.png",
				"image/png",
				"test".getBytes());
	}
}
