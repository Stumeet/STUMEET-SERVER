package com.stumeet.server.study.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;

public interface JpaStudyRepository extends JpaRepository<StudyJpaEntity, Long> {
}
