package com.stumeet.server.common.util;

import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.file.domain.ImageContentType;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileValidator {

	public static void validateFileName(String fileName) {
		if (fileName == null || !fileName.contains(".")) {
			throw new IllegalArgumentException("파일 이름이 존재하지 않습니다.");
		}
	}

	public static boolean isImageFileValid(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		String extension = FileUtil.extractExtension(fileName);

		validateFileName(fileName);
		validateImageContentType(contentType, extension);

		return true;
	}

	public static void validateImageContentType(String contentType, String extension) {
		if (contentType == null) {
			throw new BusinessException(ErrorCode.INVALID_FILE_EXCEPTION);
		}

		if (!ImageContentType.exists(contentType, extension)) {
			throw new BusinessException(ErrorCode.INVALID_FILE_EXTENSION_EXCEPTION);
		}
	}
}
