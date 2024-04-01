package com.stumeet.server.study.application.port.in;

import com.stumeet.server.study.application.port.in.response.StudyDetailResponse;

public interface StudyQueryUseCase {

	StudyDetailResponse getStudyDetailById(Long id);
}
