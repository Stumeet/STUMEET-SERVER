package com.stumeet.server.file.application.service;

import java.util.List;

import com.stumeet.server.file.domain.FileManagementPath;
import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.file.application.port.out.FileCommandPort;
import com.stumeet.server.file.application.port.dto.FileUrl;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FileUploadService implements FileUploadUseCase {

	private static final String EMPTY_URL = "";

	private final FileCommandPort fileCommandPort;

	@Override
	public FileUrl uploadUserProfileImage(Long userId, MultipartFile multipartFile) {
		if (multipartFile == null) {
			return new FileUrl(EMPTY_URL);
		}

		String path = String.format(FileManagementPath.USER_PROFILE.getPath(), userId);
		return fileCommandPort.uploadImageFile(multipartFile, path);
	}

	@Override
	public FileUrl uploadStudyMainImage(Long studyId, MultipartFile multipartFile) {
		if (multipartFile == null) {
			return new FileUrl(EMPTY_URL);
		}

		String path = String.format(FileManagementPath.STUDY.getPath(), studyId);
		return fileCommandPort.uploadImageFile(multipartFile, path);
	}

	@Override
	public List<FileUrl> uploadStudyActivityImage(Long studyId, List<MultipartFile> multipartFiles) {
		String path = String.format(FileManagementPath.STUDY_ACTIVITY.getPath(), studyId);
		return fileCommandPort.uploadImageFiles(multipartFiles, path);
	}
}
