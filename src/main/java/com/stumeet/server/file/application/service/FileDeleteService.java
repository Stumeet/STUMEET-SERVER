package com.stumeet.server.file.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.in.FileDeleteUseCase;
import com.stumeet.server.file.application.port.out.FileCommandPort;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FileDeleteService implements FileDeleteUseCase {

	private final String STUDY_PREFIX = "study/%d/";
	private final String USER_PREFIX = "user/%d/";


	private final FileCommandPort fileCommandPort;

	@Override
	public void deleteStudyRelatedImage(Long studyId) {
		fileCommandPort.deleteFolder(String.format(STUDY_PREFIX, studyId));
	}

	@Override
	public void deleteUserRelatedImage(Long userId) {
		fileCommandPort.deleteFolder(String.format(USER_PREFIX, userId));
	}
}
