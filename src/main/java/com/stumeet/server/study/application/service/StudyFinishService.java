package com.stumeet.server.study.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyFinishUseCase;
import com.stumeet.server.study.application.port.out.StudyCommandPort;
import com.stumeet.server.study.application.port.out.StudyQueryPort;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class StudyFinishService implements StudyFinishUseCase {

	private final StudyQueryPort studyQueryPort;
	private final StudyCommandPort studyCommandPort;
	private final StudyMemberValidationUseCase studyMemberValidationUseCase;

	@Override
	public void finish(Long studyId, Long memberId) {
		Study study = studyQueryPort.getById(studyId);
		studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);
		studyMemberValidationUseCase.checkAdmin(studyId, memberId);

		study.finish();

		studyCommandPort.save(study);
	}
}
