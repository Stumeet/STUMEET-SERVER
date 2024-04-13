package com.stumeet.server.study.application.port.out;

import java.util.List;

public interface StudyTagCommandPort {

	List<String> saveAll(List<String> studyTags, Long studyId);
}
