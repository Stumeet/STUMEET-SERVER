package com.stumeet.server.study.application.port.in;

import com.stumeet.server.study.adapter.in.web.response.StudyDetailResponse;

public interface StudyQueryUseCase {

	StudyDetailResponse getStudyDetailById(Long id);
}
