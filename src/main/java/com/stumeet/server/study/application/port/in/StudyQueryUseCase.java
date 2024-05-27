package com.stumeet.server.study.application.port.in;

import com.stumeet.server.study.adapter.in.web.response.JoinedStudiesResponse;
import com.stumeet.server.study.adapter.in.web.response.StudyDetailResponse;
import com.stumeet.server.study.application.port.in.command.GetJoinedStudyCommand;

public interface StudyQueryUseCase {

	StudyDetailResponse getStudyDetailById(Long id);

	JoinedStudiesResponse getJoinedStudiesByStatus(GetJoinedStudyCommand command);
}
