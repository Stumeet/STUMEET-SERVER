package com.stumeet.server.common.util;

import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.exception.model.InvalidFileException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.file.domain.ImageContentType;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileValidator {

	public static boolean isValidImageFile(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		String extension = FileUtil.extractExtension(fileName);

		return isValidFileName(fileName) && isValidFileContentType(contentType, extension);
	}

	public static void validateImageFile(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		String extension = FileUtil.extractExtension(fileName);

		validateFileName(fileName);
		validateImageContentType(contentType, extension);
	}

	public static void validateFileName(String fileName) {
		if (!isValidFileName(fileName)) {
			throw new InvalidFileException(ErrorCode.INVALID_FILE_NAME_EXCEPTION);
		}
	}

	public static void validateImageContentType(String contentType, String extension) {
		if (!isValidFileContentType(contentType, extension)) {
			throw new InvalidFileException(ErrorCode.INVALID_FILE_CONTENT_TYPE_EXCEPTION);
		}
	}

	private static boolean isValidFileName(String fileName) {
		return fileName != null && fileName.contains(".");
	}

	private static boolean isValidFileContentType(String contentType, String extension) {
		return contentType != null && ImageContentType.exists(contentType, extension);
	}
}
