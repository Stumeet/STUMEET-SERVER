package com.stumeet.server.study.application.port.out;

import java.util.List;

import com.stumeet.server.study.domain.StudyTag;

public interface StudyTagCommandPort {

	List<StudyTag> saveAll(List<StudyTag> studyTags, Long studyDomainId);
}
