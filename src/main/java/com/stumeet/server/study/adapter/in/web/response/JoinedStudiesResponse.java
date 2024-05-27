package com.stumeet.server.study.adapter.in.web.response;

import java.util.List;

public record JoinedStudiesResponse(
	List<StudySimpleResponse> studySimpleResponses
) {
}
