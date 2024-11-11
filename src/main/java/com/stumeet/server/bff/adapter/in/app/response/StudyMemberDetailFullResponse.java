package com.stumeet.server.bff.adapter.in.app.response;

import com.stumeet.server.studymember.application.port.in.response.StudyMemberDetailResponse;

public record StudyMemberDetailFullResponse(
        StudyMemberDetailResponse studyMemberDetailResponse,
        Boolean isAdmin,
        Boolean canSendGrape
) {
}
