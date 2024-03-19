package com.stumeet.server.studymember.application.port.out;

import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;

import java.util.List;

public interface StudyMemberQueryPort {
    List<SimpleStudyMemberResponse> findStudyMembers(Long studyId);
}
