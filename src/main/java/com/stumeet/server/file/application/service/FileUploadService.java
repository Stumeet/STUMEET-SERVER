package com.stumeet.server.file.application.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.file.application.port.out.FileCommandPort;
import com.stumeet.server.file.application.port.out.FileUrl;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FileUploadService implements FileUploadUseCase {

	private final static String USER_PROFILE_IMAGE_DIRECTORY_PATH = "user/%d/profile";
	private final static String STUDY_ACTIVITY_IMAGE_DIRECTORY_PATH = "study/%d/activity";

	private final FileCommandPort fileCommandPort;

	@Override
	public FileUrl uploadUserProfileImage(int userId, MultipartFile multipartFile) {
		String path = String.format(USER_PROFILE_IMAGE_DIRECTORY_PATH, userId);

		return fileCommandPort.uploadImageFile(multipartFile, path);
	}

	@Override
	public FileUrl uploadStudyActivityImage(int studyId, List<MultipartFile> multipartFiles) {
		String path = String.format(STUDY_ACTIVITY_IMAGE_DIRECTORY_PATH, studyId);

		return fileCommandPort.uploadImageFiles(multipartFiles, path);
	}
}
