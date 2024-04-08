package com.stumeet.server.study.application.port.out;

import com.stumeet.server.study.domain.StudyDomain;

public interface StudyDomainCommandPort {

	StudyDomain save(StudyDomain studyDomain);
}
