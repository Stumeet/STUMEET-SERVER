package com.stumeet.server.study.application.port.out;

import java.util.List;

public interface StudyTagCommandPort {

	List<String> saveAllStudyTags(List<String> studyTags, Long studyId);

	void clearStudyTags(Long studyId);

	void replaceStudyTags(List<String> newStudyTags, Long studyId);
}
