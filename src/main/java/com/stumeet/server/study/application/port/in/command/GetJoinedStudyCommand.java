package com.stumeet.server.study.application.port.in.command;

import com.stumeet.server.study.domain.StudyStatus;

import lombok.Builder;

public record GetJoinedStudyCommand(
	Long memberId,
	StudyStatus studyStatus
) {
	@Builder
	public GetJoinedStudyCommand(Long memberId, String studyStatus) {
		this(memberId, StudyStatus.fromValue(studyStatus));
	}
}
