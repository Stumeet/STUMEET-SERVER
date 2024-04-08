package com.stumeet.server.study.application.port.in;

import com.stumeet.server.study.domain.StudyDomain;

public interface StudyDomainCreateUseCase {

	StudyDomain create(StudyDomain studyDomain);
}
