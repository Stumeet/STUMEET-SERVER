package com.stumeet.server.studymember.application.port.in;

import com.stumeet.server.studymember.application.port.in.response.StudyMemberResponses;

public interface StudyMemberQueryUseCase {
    StudyMemberResponses getStudyMembers(Long studyId, Long memberId);
}
