package com.stumeet.server.studymember.application.port.in;

import java.util.List;

import com.stumeet.server.studymember.application.port.in.response.StudyMemberAdminResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberDetailResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberResponses;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberReviewStatusResponse;

public interface StudyMemberQueryUseCase {
    StudyMemberResponses getStudyMembers(Long studyId, Long requesterId);

    StudyMemberDetailResponse getStudyMemberDetail(Long studyId, Long targetMemberId, Long requesterId);

    StudyMemberAdminResponse isMemberAdmin(Long studyId, Long memberId);

    boolean canSendGrape(Long studyId, Long memberId);

    List<StudyMemberReviewStatusResponse> getStudyMemberReviewStatusByMember(Long studyId, Long memberId);
}
