package com.stumeet.server.member.adapter.out.persistence;

import com.stumeet.server.member.domain.Member;
import com.stumeet.server.member.domain.OAuthLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthLoginPersistenceMapper {

    private final MemberPersistenceMapper memberPersistenceMapper;

    public OAuthLoginJpaEntity toEntity(OAuthLogin domain) {
        return OAuthLoginJpaEntity.builder()
                .id(domain.getId())
                .member(memberPersistenceMapper.toEntity(domain.getMember()))
                .providerName(domain.getProviderName())
                .providerId(domain.getProviderId())
                .build();
    }

    public OAuthLogin toDomain(OAuthLoginJpaEntity entity) {
        return OAuthLogin.builder()
                .id(entity.getId())
                .member(memberPersistenceMapper.toDomain(entity.getMember()))
                .providerName(entity.getProviderName())
                .providerId(entity.getProviderId())
                .build();
    }
}
