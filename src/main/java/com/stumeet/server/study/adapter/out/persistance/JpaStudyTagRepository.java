package com.stumeet.server.study.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyTagJpaEntity;

public interface JpaStudyTagRepository extends JpaRepository<StudyTagJpaEntity, Long> {

	@Modifying
	@Query("DELETE FROM StudyTagJpaEntity s WHERE s.studyId = :studyId")
	void deleteAllByStudyId(@Param("studyId") Long studyId);
}
