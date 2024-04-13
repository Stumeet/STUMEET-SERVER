package com.stumeet.server.study.application.port.in;

import com.stumeet.server.study.application.port.in.command.StudyUpdateCommand;

public interface StudyUpdateUseCase {

	void update(Long studyId, Long memberId, StudyUpdateCommand command);
}
