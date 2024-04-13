package com.stumeet.server.study.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyTagJpaEntity;

public interface JpaStudyTagRepository extends JpaRepository<StudyTagJpaEntity, Long> {

	void deleteAllByStudyId(Long studyId);
}
