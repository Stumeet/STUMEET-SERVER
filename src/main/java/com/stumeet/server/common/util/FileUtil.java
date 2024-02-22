package com.stumeet.server.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {

	private static final Set<String> VALID_CONTENT_TYPES = new HashSet<>();

	static {
		VALID_CONTENT_TYPES.add("jpg");
		VALID_CONTENT_TYPES.add("jpeg");
		VALID_CONTENT_TYPES.add("png");
	}

	public static String getContentType(String fileName) {
		if (fileName.isEmpty()) {
			throw new BusinessException(ErrorCode.INVALID_IMAGE_EXCEPTION);
		}

		String contentType = fileName
			.substring(fileName.lastIndexOf(".") + 1)
			.toLowerCase(Locale.ROOT);

		if (!VALID_CONTENT_TYPES.contains(contentType)) {
			throw new BusinessException(ErrorCode.INVALID_FILE_EXTENSION_EXCEPTION);
		}

		return contentType;
	}

	public static String createFileName(String directoryPath, String fileName) {
		String dateTime = LocalDateTime.now()
			.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		return String.format("%s/%s%s-%s", directoryPath, dateTime, UUID.randomUUID(), fileName);
	}
}
