package com.stumeet.server.study.application.service;

import com.stumeet.server.file.application.port.out.FileUrl;
import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import com.stumeet.server.study.application.port.out.StudyTagCommandPort;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.study.application.port.in.StudyCreateUseCase;
import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.application.port.in.mapper.StudyUseCaseMapper;
import com.stumeet.server.study.application.port.out.StudyCommandPort;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.studymember.application.port.in.StudyMemberJoinUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class StudyCreateService implements StudyCreateUseCase {

	private final FileUploadUseCase fileUploadUseCase;
	private final StudyMemberJoinUseCase memberJoinUseCase;
	private final MemberValidationUseCase memberValidationUseCase;

	private final StudyCommandPort studyCommandPort;
	private final StudyTagCommandPort studyTagCommandPort;

	private final StudyUseCaseMapper studyUseCaseMapper;

	@Override
	public Long create(StudyCreateCommand command, Long memberId) {
		memberValidationUseCase.checkById(memberId);

		FileUrl mainImageUrl = fileUploadUseCase.uploadStudyMainImage(command.image());
		Study study = Study.create(command, mainImageUrl.url());

		Long studyCreatedId = studyCommandPort.save(study).getId();
		studyTagCommandPort.saveAllStudyTags(study.getStudyTags(), studyCreatedId);

		memberJoinUseCase.join(studyUseCaseMapper.toAdminStudyMemberJoinCommand(memberId, studyCreatedId));
		return studyCreatedId;
	}
}
