package com.stumeet.server.studymember.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStudyMemberRepository extends JpaRepository<StudyMemberJpaEntity, Long>, JpaStudyMemberRepositoryCustom {
    long countByStudyId(Long studyId);

    void deleteByStudyIdAndMemberId(Long studyId, Long memberId);

    void deleteAllByStudy_Id(Long studyId);
}
