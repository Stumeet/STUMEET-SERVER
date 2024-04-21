package com.stumeet.server.study.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import com.stumeet.server.study.application.port.in.StudyDeleteUseCase;
import com.stumeet.server.study.application.port.out.StudyCommandPort;
import com.stumeet.server.study.application.port.out.StudyTagCommandPort;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.out.StudyMemberLeavePort;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class StudyDeleteService implements StudyDeleteUseCase {

	private final MemberValidationUseCase memberValidationUseCase;
	private final StudyMemberValidationUseCase studyMemberValidationUseCase;

	private final StudyCommandPort studyCommandPort;
	private final StudyTagCommandPort studyTagCommandPort;
	private final StudyMemberLeavePort studyMemberLeavePort;

	@Override
	public void delete(Long studyId, Long memberId) {
		memberValidationUseCase.checkById(memberId);
		studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);
		studyMemberValidationUseCase.checkAdmin(studyId, memberId);

		studyTagCommandPort.clearStudyTags(studyId);
		studyMemberLeavePort.removeAllStudyMember(studyId);
		studyCommandPort.delete(studyId);
	}
}

