package com.stumeet.server.study.application.service;

import com.stumeet.server.file.application.port.out.FileUrl;
import com.stumeet.server.study.application.port.out.StudyTagCommandPort;
import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.member.domain.Member;
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

	private final StudyCommandPort studyCommandPort;
	private final StudyTagCommandPort studyTagCommandPort;

	private final StudyUseCaseMapper studyUseCaseMapper;

	@Override
	public Long create(StudyCreateCommand command, Member member) {
		Study study = studyUseCaseMapper.toDomain(command);
		FileUrl mainImageUrl = fileUploadUseCase.uploadStudyMainImage(command.image());
		study.setImageUrl(mainImageUrl);

		Long studyCreatedId = studyCommandPort.save(study).getId();
		studyTagCommandPort.saveAll(study.getStudyTags(), studyCreatedId);

		memberJoinUseCase.join(studyUseCaseMapper.toAdminStudyMemberJoinCommand(member.getId(), studyCreatedId));
		return studyCreatedId;
	}
}
