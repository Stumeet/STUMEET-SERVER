package com.stumeet.server.studymember.adapter.out.persistence;

import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;

import java.util.List;

public interface JpaStudyMemberRepositoryCustom {
    List<SimpleStudyMemberResponse> findStudyMembersByStudyId(Long studyId);

    boolean isStudyJoinMember(Long studyId, Long memberId);

    boolean isAdmin(Long studyId, Long adminId);

}
