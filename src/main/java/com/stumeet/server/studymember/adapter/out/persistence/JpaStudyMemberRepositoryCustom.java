package com.stumeet.server.studymember.adapter.out.persistence;

import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;

import java.util.List;

public interface JpaStudyMemberRepositoryCustom {

    StudyMemberJpaEntity findStudyMemberByStudyIdAndMemberId(Long studyId, Long memberId);

    List<SimpleStudyMemberResponse> findStudyMembersByStudyId(Long studyId);

    boolean isStudyJoinMember(Long studyId, Long memberId);

    boolean isAdmin(Long studyId, Long adminId);

    boolean isSentGrape(Long studyId, Long memberId);
}
