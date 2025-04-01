package com.stumeet.server.study.adapter.out.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;

public interface JpaStudyRepository extends JpaRepository<StudyJpaEntity, Long> {

	@Query("SELECT s "
		+ "FROM StudyJpaEntity s "
		+ "WHERE s.id "
		+ "IN (SELECT sm.study.id FROM StudyMemberJpaEntity sm WHERE sm.member.id = :memberId ORDER BY sm.createdAt desc) "
		+ "AND s.isFinished = false "
		+ "AND s.isDeleted = false")
	List<StudyJpaEntity> findActivesByMemberIdOrderByJoinedDate(@Param("memberId") Long memberId);


	@Query("SELECT s "
			+ "FROM StudyJpaEntity s "
			+ "JOIN StudyMemberJpaEntity sm ON s.id = sm.study.id "
			+ "WHERE sm.member.id = :memberId "
			+ "AND sm.isLegacyHidden = false "
			+ "AND s.isFinished = true "
			+ "AND s.isDeleted = false "
			+ "ORDER BY s.startDate ASC")
	List<StudyJpaEntity> findLegacyStudiesNotHidden(@Param("memberId") Long memberId);
}
