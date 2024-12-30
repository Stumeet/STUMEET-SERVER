package com.stumeet.server.studymember.adapter.out.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stumeet.server.studymember.adapter.out.persistence.entity.GrapeJpaEntity;

public interface JpaGrapeRepository extends JpaRepository<GrapeJpaEntity, Long> {

    Page<GrapeJpaEntity> findAllByMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);
}