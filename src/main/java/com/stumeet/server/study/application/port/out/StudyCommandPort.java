package com.stumeet.server.study.application.port.out;

import com.stumeet.server.study.domain.Study;

public interface StudyCommandPort {

	Study save(Study study);

	void update(Study study);

	void delete(Long studyId);
}
