package com.stumeet.server.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<MemberJpaEntity, Long>, JpaMemberRepositoryCustom {
}
