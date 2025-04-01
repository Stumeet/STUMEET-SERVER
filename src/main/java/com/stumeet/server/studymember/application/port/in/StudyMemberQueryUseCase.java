package com.stumeet.server.studymember.application.port.in;

import java.util.List;

import com.stumeet.server.studymember.application.port.in.response.MyStudyMemberDetailResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberDetailResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberResponses;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberReviewStatusResponse;

public interface StudyMemberQueryUseCase {
    StudyMemberResponses getStudyMembers(Long studyId, Long requesterId);

    StudyMemberDetailResponse getStudyMemberDetail(Long studyId, Long targetMemberId, Long requesterId);

    List<StudyMemberReviewStatusResponse> getStudyMemberReviewStatusByMember(Long studyId, Long memberId);

    MyStudyMemberDetailResponse getMyStudyMemberDetail(Long studyId, Long memberId);

    long getStudyMemberCount(Long studyId);

    boolean isMemberAdmin(Long studyId, Long memberId);
}
