package com.stumeet.server.profession.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProfessionRepository extends JpaRepository<ProfessionJpaEntity, Long> {
}
