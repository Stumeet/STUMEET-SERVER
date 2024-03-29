package com.stumeet.server.study.application.port.out.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.in.web.response.StudyDetailResponse;
import com.stumeet.server.study.domain.Study;

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
			.meetingRepeatType(study.getMeetingRepeatTypeName())
			.meetingRepeatDays(study.getMeetingRepeatDays())
			.isFinished(study.isFinished())
			.isDeleted(study.isDeleted())
			.build();
	}
}
