package com.stumeet.server.file.domain;

import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.common.util.FileUtil;

import lombok.Getter;

@Getter
public class ImageFile {

	private final MultipartFile file;

	private final String name;

	private final String contentType;

	private final String extension;

	public ImageFile(MultipartFile file) {
		this.file = file;
		this.name = file.getOriginalFilename();
		this.contentType = file.getContentType();
		this.extension = FileUtil.extractExtension(name);
		validate();
	}

	private void validate() {
		if (contentType == null || extension == null) {
			throw new BusinessException(ErrorCode.INVALID_IMAGE_FILE_EXCEPTION);
		}

		if (!ImageContentType.isValid(contentType, extension)) {
			throw new BusinessException(ErrorCode.INVALID_FILE_EXTENSION_EXCEPTION);
		}
	}
}
