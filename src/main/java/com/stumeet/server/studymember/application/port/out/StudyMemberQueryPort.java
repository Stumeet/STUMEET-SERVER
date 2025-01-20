package com.stumeet.server.studymember.application.port.out;

import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberReviewStatusResponse;
import com.stumeet.server.studymember.domain.StudyMember;

import java.util.List;

public interface StudyMemberQueryPort {
    List<SimpleStudyMemberResponse> findStudyMembers(Long studyId);

    StudyMember findStudyMember(Long studyId, Long memberId);

    StudyMember findStudyMember(Long studyMemberId);

    boolean isSentGrape(Long studyId, Long memberId);

    List<StudyMemberReviewStatusResponse> findStudyMemberReviewStatusByMember(Long studyId, Long memberId);
}
