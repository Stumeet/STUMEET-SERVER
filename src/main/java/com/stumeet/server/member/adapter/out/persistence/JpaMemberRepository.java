package com.stumeet.server.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<MemberJpaEntity, Long>, JpaMemberRepositoryCustom {
    Optional<MemberJpaEntity> findByName(String name);
}
