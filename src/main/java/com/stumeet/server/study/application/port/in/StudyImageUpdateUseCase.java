package com.stumeet.server.study.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface StudyImageUpdateUseCase {

	void updateMainImage(Long studyId, MultipartFile imageFile);
}
