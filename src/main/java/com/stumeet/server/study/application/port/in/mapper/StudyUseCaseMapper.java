package com.stumeet.server.study.application.port.in.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.application.port.in.response.StudyDetailResponse;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;

@Component
public class StudyUseCaseMapper {

	public StudyDetailResponse toStudyDetailResponse(Study study) {
		return StudyDetailResponse.builder()
			.id(study.getId())
			.name(study.getName())
			.field(study.getStudyFieldName())
			.tags(study.getStudyTagNames())
			.region(study.getRegion())
			.intro(study.getIntro())
			.rule(study.getRule())
			.startDate(study.getStartDate())
			.endDate(study.getEndDate())
			.headcount(study.getHeadcountNumber())
			.image(study.getImage())
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
