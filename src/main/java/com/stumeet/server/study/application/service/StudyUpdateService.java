package com.stumeet.server.study.application.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
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
	private final FileUploadUseCase fileUploadUseCase;

	private final StudyQueryPort studyQueryPort;
	private final StudyCommandPort studyCommandPort;
	private final StudyTagCommandPort studyTagCommandPort;

	@Override
	public void update(Long studyId, Long memberId, StudyUpdateCommand command, MultipartFile mainImageFile) {
		memberValidationUseCase.checkById(memberId);
		studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);
		studyMemberValidationUseCase.checkAdmin(studyId, memberId);

		Study existingStudy = studyQueryPort.getById(studyId);

		String mainImageUrl = mainImageFile != null
			? fileUploadUseCase.uploadStudyMainImage(mainImageFile).url()
			: existingStudy.getImageUrl();

		Study updatedStudy = Study.update(command, existingStudy, mainImageUrl);
		studyCommandPort.save(updatedStudy);

		if (!existingStudy.isStudyTagsEquals(updatedStudy.getStudyTags())) {
			studyTagCommandPort.replaceStudyTags(updatedStudy.getStudyTags(), updatedStudy.getId());
		}
	}
}
