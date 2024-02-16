package com.stumeet.server.member.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.member.application.port.out.OAuthLoginCommandPort;
import com.stumeet.server.member.application.port.out.OAuthLoginQueryPort;
import com.stumeet.server.member.domain.OAuthLogin;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class OAuthLoginPersistenceAdapter implements OAuthLoginQueryPort, OAuthLoginCommandPort {

    private final JpaOAuthLoginRepository jpaOAuthLoginRepository;
    private final OAuthLoginPersistenceMapper oAuthLoginMapper;

    public boolean existsByProviderId(String providerId) {
        return jpaOAuthLoginRepository.existsOAuthLoginJpaEntityByProviderId(providerId);
    }

    @Override
    public OAuthLogin save(OAuthLogin oAuthLogin) {
        OAuthLoginJpaEntity entity = jpaOAuthLoginRepository.save(oAuthLoginMapper.toEntity(oAuthLogin));

        return oAuthLoginMapper.toDomain(entity);
    }
}
