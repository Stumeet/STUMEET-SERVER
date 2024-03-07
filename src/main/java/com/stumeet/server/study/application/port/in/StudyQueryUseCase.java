package com.stumeet.server.study.application.port.in;

import com.stumeet.server.study.domain.Study;

public interface StudyQueryUseCase {

	Study getById(Long id);
}
