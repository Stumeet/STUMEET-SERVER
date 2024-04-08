package com.stumeet.server.study.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.member.domain.Member;
import com.stumeet.server.study.application.port.in.StudyCreateUseCase;
import com.stumeet.server.study.application.port.in.StudyDomainCreateUseCase;
import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.application.port.in.mapper.StudyDomainUseCaseMapper;
import com.stumeet.server.study.application.port.in.mapper.StudyUseCaseMapper;
import com.stumeet.server.study.application.port.out.StudyCommandPort;
import com.stumeet.server.study.application.port.out.StudyFieldQueryPort;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.study.domain.StudyDomain;
import com.stumeet.server.studymember.application.port.in.StudyMemberJoinUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class StudyCreateService implements StudyCreateUseCase {

	private final FileUploadUseCase fileUploadUseCase;
	private final StudyMemberJoinUseCase memberJoinUseCase;
	private final StudyDomainCreateUseCase studyDomainCreateUseCase;

	private final StudyCommandPort studyCommandPort;
	private final StudyFieldQueryPort studyFieldQueryPort;

	private final StudyUseCaseMapper studyUseCaseMapper;
	private final StudyDomainUseCaseMapper studyDomainUseCaseMapper;

	@Override
	public Long create(StudyCreateCommand command, Member member) {
		studyFieldQueryPort.checkById(command.studyFieldId());

		StudyDomain studyDomainCreated = studyDomainCreateUseCase.create(studyDomainUseCaseMapper.toDomain(command));
		String mainImageUrl = fileUploadUseCase.uploadStudyMainImage(command.image()).url();

		Study study = Study.create(command, studyDomainCreated, mainImageUrl);
		Study studyCreated = studyCommandPort.save(study);

		memberJoinUseCase.join(studyUseCaseMapper.toAdminStudyMemberJoinCommand(member.getId(), studyCreated.getId()));
		return studyCreated.getId();
	}
}
