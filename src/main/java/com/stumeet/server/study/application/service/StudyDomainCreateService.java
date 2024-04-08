package com.stumeet.server.study.application.service;

import java.util.List;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyDomainCreateUseCase;
import com.stumeet.server.study.application.port.out.StudyDomainCommandPort;
import com.stumeet.server.study.application.port.out.StudyTagCommandPort;
import com.stumeet.server.study.domain.StudyDomain;
import com.stumeet.server.study.domain.StudyTag;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class StudyDomainCreateService implements StudyDomainCreateUseCase {

	private final StudyDomainCommandPort studyDomainCommandPort;
	private final StudyTagCommandPort studyTagCommandPort;

	@Override
	public StudyDomain create(StudyDomain studyDomain) {
		StudyDomain created = studyDomainCommandPort.save(studyDomain);
		if (created.isStudyTagNotEmpty()) {
			createStudyTags(created.getStudyTags(), created.getId());
		}

		return created;
	}

	private List<StudyTag> createStudyTags(List<StudyTag> studyTags, Long studyDomainId) {
		return studyTagCommandPort.saveAll(studyTags, studyDomainId);
	}
}
