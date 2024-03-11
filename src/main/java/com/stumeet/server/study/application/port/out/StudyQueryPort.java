package com.stumeet.server.study.application.port.out;

import com.stumeet.server.study.domain.Study;

public interface StudyQueryPort {

	Study getById(Long id);
}
