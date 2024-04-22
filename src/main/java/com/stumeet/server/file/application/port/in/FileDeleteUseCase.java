package com.stumeet.server.file.application.port.in;

public interface FileDeleteUseCase {

	void deleteStudyRelatedImage(Long studyId);

	void deleteUserRelatedImage(Long userId);
}
