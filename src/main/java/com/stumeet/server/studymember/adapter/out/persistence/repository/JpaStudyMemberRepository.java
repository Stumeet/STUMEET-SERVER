package com.stumeet.server.studymember.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.stumeet.server.studymember.adapter.out.persistence.entity.StudyMemberJpaEntity;

public interface JpaStudyMemberRepository extends JpaRepository<StudyMemberJpaEntity, Long>, JpaStudyMemberRepositoryCustom {
    long countByStudyId(Long studyId);

    void deleteByStudyIdAndMemberId(Long studyId, Long memberId);

    void deleteAllByStudyId(Long studyId);

    @Modifying
    @Query("UPDATE StudyMemberJpaEntity sm "
            + "SET sm.isAdmin = false "
            + "WHERE sm.study.id = :studyId "
            + "AND sm.member.id = :adminId")
    void removeAdmin(Long studyId, Long adminId);

    @Modifying
    @Query("UPDATE StudyMemberJpaEntity sm "
            + "SET sm.isAdmin = true "
            + "WHERE sm.study.id = :studyId "
            + "AND sm.member.id = :memberId")
    void appointAdmin(Long studyId, Long memberId);
}
