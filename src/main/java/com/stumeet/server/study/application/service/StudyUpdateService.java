package com.stumeet.server.study.application.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import com.stumeet.server.study.application.port.in.StudyImageUpdateUseCase;
import com.stumeet.server.study.application.port.in.StudyUpdateUseCase;
import com.stumeet.server.study.application.port.in.command.StudyUpdateCommand;
import com.stumeet.server.study.application.port.out.StudyCommandPort;
import com.stumeet.server.study.application.port.out.StudyQueryPort;
import com.stumeet.server.study.application.port.out.StudyTagCommandPort;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class StudyUpdateService implements StudyUpdateUseCase {

	private final MemberValidationUseCase memberValidationUseCase;
	private final StudyMemberValidationUseCase studyMemberValidationUseCase;
	private final StudyImageUpdateUseCase studyImageUpdateUseCase;

	private final StudyQueryPort studyQueryPort;
	private final StudyCommandPort studyCommandPort;
	private final StudyTagCommandPort studyTagCommandPort;

	@Override
	public void update(Long studyId, Long memberId, StudyUpdateCommand command, MultipartFile mainImageFile) {
		validateMemberCanUpdate(studyId, memberId);

		Study existingStudy = studyQueryPort.getById(studyId);
		Study updatedStudy = Study.update(command, existingStudy);
		studyCommandPort.save(updatedStudy);
		studyImageUpdateUseCase.updateMainImage(studyId, mainImageFile);

		if (existingStudy.isStudyTagChanged(updatedStudy.getStudyTags())) {
			studyTagCommandPort.replaceStudyTags(updatedStudy.getStudyTags(), updatedStudy.getId());
		}
	}

	private void validateMemberCanUpdate(long studyId, long memberId) {
		memberValidationUseCase.checkById(memberId);
		studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);
		studyMemberValidationUseCase.checkAdmin(studyId, memberId);
	}
}
