package com.stumeet.server.study.application.port.in.mapper;

import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.stumeet.server.study.application.port.in.response.StudyDetailResponse;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;

@Component
@RequiredArgsConstructor
public class StudyUseCaseMapper {

	private final StudyDomainUseCaseMapper studyDomainUseCaseMapper;
	private final StudyPeriodUseCaseMapper studyPeriodUseCaseMapper;
	private final StudyMeetingScheduleUseCaseMapper studyMeetingScheduleUseCaseMapper;

	public Study toDomain(StudyCreateCommand command) {
		return Study.create(
				studyDomainUseCaseMapper.toDomain(command),
				command.name(),
				command.intro(),
				command.rule(),
				command.region(),
				studyPeriodUseCaseMapper.toDomain(command),
				studyMeetingScheduleUseCaseMapper.toDomain(command));
	}

	public StudyDetailResponse toStudyDetailResponse(Study study) {
		return StudyDetailResponse.builder()
			.id(study.getId())
			.name(study.getName())
			.field(study.getStudyFieldName())
			.tags(study.getStudyTags())
			.region(study.getRegion())
			.intro(study.getIntro())
			.rule(study.getRule())
			.startDate(study.getStartDate())
			.endDate(study.getEndDate())
			.image(study.getImageUrl())
			.meetingTime(study.getMeetingTime())
			.meetingRepetitionType(study.getMeetingRepeatTypeName())
			.meetingRepetitionDates(study.getRepetitionDates())
			.isFinished(study.isFinished())
			.isDeleted(study.isDeleted())
			.build();
	}

	public StudyMemberJoinCommand toAdminStudyMemberJoinCommand(Long memberId, Long studyId) {
		return StudyMemberJoinCommand.builder()
				.memberId(memberId)
				.studyId(studyId)
				.isAdmin(true)
				.build();
	}
}
