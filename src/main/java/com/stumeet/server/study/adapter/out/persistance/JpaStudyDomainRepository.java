package com.stumeet.server.study.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyDomainJpaEntity;

public interface JpaStudyDomainRepository extends JpaRepository<StudyDomainJpaEntity, Long> {
}
