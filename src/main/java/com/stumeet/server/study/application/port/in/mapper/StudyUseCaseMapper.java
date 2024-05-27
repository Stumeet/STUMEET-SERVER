package com.stumeet.server.study.application.port.in.mapper;

import static java.util.stream.Collectors.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.in.web.response.JoinedStudiesResponse;
import com.stumeet.server.study.adapter.in.web.response.StudyDetailResponse;
import com.stumeet.server.study.adapter.in.web.response.StudySimpleResponse;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;

@Component
@RequiredArgsConstructor
public class StudyUseCaseMapper {

	public StudyDetailResponse toStudyDetailResponse(Study study) {
		return StudyDetailResponse.builder()
			.id(study.getId())
			.name(study.getName())
			.field(study.getStudyFieldName())
			.tags(study.getStudyTags())
			.region(study.getRegion())
			.intro(study.getIntro())
			.rule(study.getRule())
			.headcount(study.getHeadcount())
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

	public StudySimpleResponse toStudySimpleResponse(Study study) {
		return StudySimpleResponse.builder()
			.id(study.getId())
			.name(study.getName())
			.field(study.getStudyFieldName())
			.tags(study.getStudyTags())
			.image(study.getImageUrl())
			.headcount(study.getHeadcount())
			.startDate(study.getPeriod().getStartDate())
			.endDate(study.getPeriod().getEndDate())
			.build();
	}

	public JoinedStudiesResponse toMyStudiesResponse(List<Study> studies) {
		return studies.stream()
			.map(this::toStudySimpleResponse)
			.collect(collectingAndThen(toList(), JoinedStudiesResponse::new));
	}
}