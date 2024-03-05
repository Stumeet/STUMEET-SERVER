package com.stumeet.server.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.common.util.model.ValidImageContentType;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {

	private static final String FILE_NAME_FORMAT = "%s/%s%s-%s";
	private static final String FILE_DATE_TIME_FORMAT = "yyyyMMddHHmmss";

	public static String getValidImageContentType(String contentType) {
		validateImageContentType(contentType);

		return contentType;
	}

	private static void validateImageContentType(String contentType) {
		if (contentType == null) {
			throw new BusinessException(ErrorCode.INVALID_IMAGE_EXCEPTION);
		}

		if (!isValidImageFile(contentType)) {
			throw new BusinessException(ErrorCode.INVALID_FILE_EXTENSION_EXCEPTION);
		}
	}

	public static boolean isValidImageFile(String contentType) {
		return ValidImageContentType.hasContentType(contentType);
	}

    public static String createFileName(String directoryPath, String fileName) {
		String dateTime = LocalDateTime.now()
			.format(DateTimeFormatter.ofPattern(FILE_DATE_TIME_FORMAT));

		return String.format(FILE_NAME_FORMAT, directoryPath, dateTime, UUID.randomUUID(), fileName);
	}
}
