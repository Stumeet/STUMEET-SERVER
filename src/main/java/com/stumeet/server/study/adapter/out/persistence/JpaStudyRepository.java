package com.stumeet.server.study.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStudyRepository extends JpaRepository<StudyJpaEntity, Long>, JpaStudyRepositoryCustom {
}
