package com.stumeet.server.study.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.member.domain.Member;
import com.stumeet.server.study.application.port.in.StudyCreateUseCase;
import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.application.port.in.mapper.MeetingScheduleUseCaseMapper;
import com.stumeet.server.study.application.port.in.mapper.StudyDomainUseCaseMapper;
import com.stumeet.server.study.application.port.out.StudyCommandPort;
import com.stumeet.server.study.application.port.out.StudyDomainCommandPort;
import com.stumeet.server.study.application.port.out.StudyFieldQueryPort;
import com.stumeet.server.study.application.port.out.StudyTagCommandPort;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.study.domain.StudyDomain;
import com.stumeet.server.study.domain.StudyHeadCount;
import com.stumeet.server.study.domain.StudyPeriod;
import com.stumeet.server.studymember.application.port.in.StudyMemberJoinUseCase;
import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class StudyCreateService implements StudyCreateUseCase {

	private final FileUploadUseCase fileUploadUseCase;
	private final StudyMemberJoinUseCase memberJoinUseCase;

	private final StudyCommandPort studyCommandPort;
	private final StudyFieldQueryPort studyFieldQueryPort;
	private final StudyDomainCommandPort studyDomainCommandPort;
	private final StudyTagCommandPort studyTagCommandPort;

	private final StudyDomainUseCaseMapper studyDomainUseCaseMapper;
	private final MeetingScheduleUseCaseMapper meetingScheduleUseCaseMapper;

	@Override
	@Transactional
	public Long create(StudyCreateCommand command, Member member) {
		studyFieldQueryPort.checkById(command.studyFieldId());

		StudyDomain studyDomainCreated = createStudyDomain(studyDomainUseCaseMapper.toDomain(command));
		String mainImageUrl = command.image() != null
				? fileUploadUseCase.uploadStudyMainImage(command.image()).url()
				: null;

		Study study = Study.builder()
				.studyDomain(studyDomainCreated)
				.name(command.name())
				.intro(command.intro())
				.rule(command.rule())
				.region(command.region())
				.period(StudyPeriod.of(command.startDate(), command.endDate()))
				.meetingSchedule(meetingScheduleUseCaseMapper.toDomain(command))
				.headcount(StudyHeadCount.from(1))
				.image(mainImageUrl)
				.isFinished(false)
				.isDeleted(false)
				.build();

		Study studyCreated = studyCommandPort.save(study);

		StudyMemberJoinCommand studyMemberJoinCommand = StudyMemberJoinCommand.builder()
				.memberId(member.getId())
				.studyId(studyCreated.getId())
				.isAdmin(true)
				.build();

		memberJoinUseCase.join(studyMemberJoinCommand);

		return studyCreated.getId();
	}

	private StudyDomain createStudyDomain(StudyDomain studyDomain) {
		StudyDomain created = studyDomainCommandPort.save(studyDomain);
		studyTagCommandPort.saveAll(created.getStudyTags(), created.getId());

		return created;
	}
}
