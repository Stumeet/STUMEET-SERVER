package com.stumeet.server.study.application.service;

import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.dto.FileUrl;
import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.study.application.port.in.StudyImageUpdateUseCase;
import com.stumeet.server.study.application.port.out.StudyCommandPort;
import com.stumeet.server.study.application.port.out.StudyQueryPort;
import com.stumeet.server.study.domain.Study;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class StudyImageUpdateService implements StudyImageUpdateUseCase {

	private final FileUploadUseCase fileUploadUseCase;

	private final StudyQueryPort studyQueryPort;
	private final StudyCommandPort studyCommandPort;

	@Override
	public void updateMainImage(Long studyId, MultipartFile imageFile) {
		if (imageFile != null) {
			Study study = studyQueryPort.getById(studyId);

			FileUrl mainImageUrl = fileUploadUseCase.uploadStudyMainImage(study.getId(), imageFile);
			study.updateMainImageUrl(mainImageUrl.url());
			studyCommandPort.save(study);
		}
	}
}
