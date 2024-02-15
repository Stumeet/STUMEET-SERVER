package com.stumeet.server.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOAuthLoginRepository extends JpaRepository<OAuthLoginJpaEntity, Long> {
    boolean existsOAuthLoginJpaEntityByProviderId(String providerId);
}
