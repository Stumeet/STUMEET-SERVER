package com.stumeet.server.common.util;

import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.exception.model.InvalidFileException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.file.domain.ImageContentType;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileValidator {

	public static boolean isImageFileValid(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		String extension = FileUtil.extractExtension(fileName);

		validateFileName(fileName);
		validateImageContentType(contentType, extension);

		return true;
	}

	public static void validateImageFile(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		String extension = FileUtil.extractExtension(fileName);

		validateFileName(fileName);
		validateImageContentType(contentType, extension);
	}

	public static void validateFileName(String fileName) {
		if (fileName == null || !fileName.contains(".")) {
			throw new InvalidFileException(ErrorCode.INVALID_FILE_NAME_EXCEPTION);
		}
	}

	public static void validateImageContentType(String contentType, String extension) {
		if (contentType == null) {
			throw new InvalidFileException(ErrorCode.INVALID_FILE_CONTENT_TYPE_EXCEPTION);
		}

		if (!ImageContentType.exists(contentType, extension)) {
			throw new InvalidFileException(ErrorCode.UNSUPPORTED_FILE_CONTENT_TYPE);
		}
	}
}
