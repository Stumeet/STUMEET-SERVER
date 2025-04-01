package com.stumeet.server.study.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.in.FileDeleteUseCase;
import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import com.stumeet.server.study.application.port.in.StudyDeleteUseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.study.application.port.out.StudyCommandPort;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.out.StudyMemberLeavePort;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class StudyDeleteService implements StudyDeleteUseCase {

	private final MemberValidationUseCase memberValidationUseCase;
	private final StudyValidationUseCase studyValidationUseCase;
	private final StudyMemberValidationUseCase studyMemberValidationUseCase;
	private final FileDeleteUseCase fileDeleteUseCase;

	private final StudyCommandPort studyCommandPort;
	private final StudyMemberLeavePort studyMemberLeavePort;

	@Override
	public void delete(Long studyId, Long memberId) {
		validateMemberCanDelete(studyId, memberId);

		studyMemberLeavePort.removeAllStudyMember(studyId);
		studyCommandPort.delete(studyId);

		fileDeleteUseCase.deleteStudyRelatedImage(studyId);
	}

	private void validateMemberCanDelete(long studyId, long memberId) {
		memberValidationUseCase.checkById(memberId);
		studyValidationUseCase.checkById(studyId);
		studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);
		studyMemberValidationUseCase.checkAdmin(studyId, memberId);
	}
}

