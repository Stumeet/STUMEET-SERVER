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
			.topics(study.getStudyTopics().getTopics())
			.region(study.getRegion())
			.intro(study.getIntro())
			.rule(study.getRule())
			.startDate(study.getStartDate())
			.endDate(study.getEndDate())
			.headCount(study.getHeadCountNumber())
			.mainImage(study.getMainImage())
			.isFinished(study.getIsFinished())
			.isDeleted(study.getIsDeleted())
			.build();
	}
}
