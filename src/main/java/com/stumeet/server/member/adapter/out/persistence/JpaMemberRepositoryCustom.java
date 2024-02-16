package com.stumeet.server.member.adapter.out.persistence;

import com.stumeet.server.member.domain.OAuthProvider;

public interface JpaMemberRepositoryCustom {
    MemberJpaEntity getByOAuthProviderId(String oAuthProviderId, OAuthProvider provider);
}
