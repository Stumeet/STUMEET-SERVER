package com.stumeet.server.study.application.port.out;

import com.stumeet.server.study.domain.StudyField;

public interface StudyFieldQueryPort {

	void checkById(Long id);

	StudyField getById(Long id);
}
