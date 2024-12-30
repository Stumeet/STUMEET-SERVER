package com.stumeet.server.studymember.adapter.out.persistence.repository;

import com.stumeet.server.studymember.adapter.out.persistence.entity.StudyMemberJpaEntity;
import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;

import java.util.List;
import java.util.Optional;

public interface JpaStudyMemberRepositoryCustom {

    Optional<StudyMemberJpaEntity> findStudyMemberByStudyIdAndMemberId(Long studyId, Long memberId);

    List<SimpleStudyMemberResponse> findStudyMembersByStudyId(Long studyId);

    boolean isStudyJoinMember(Long studyId, Long memberId);

    boolean isAdmin(Long studyId, Long adminId);

    boolean isSentGrape(Long studyId, Long memberId);
}
