package com.stumeet.server.studymember.application.port.in.response;

import java.util.List;

public record StudyMemberResponses(
        List<SimpleStudyMemberResponse> studyMembers
) {
}
